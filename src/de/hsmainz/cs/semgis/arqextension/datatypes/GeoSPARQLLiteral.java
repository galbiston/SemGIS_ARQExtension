/** *****************************************************************************
 * Copyright (c) 2017 Timo Homburg, i3Mainz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the BSD License
 * which accompanies this distribution, and is available at
 * https://directory.fsf.org/wiki/License:BSD_4Clause
 *
 * This project extends work by Ian Simmons who developed the Parliament Triple Store.
 * http://parliament.semwebcentral.org and published his work und BSD License as well.
 *
 *
 ****************************************************************************** */
package de.hsmainz.cs.semgis.arqextension.datatypes;

import de.hsmainz.cs.semgis.arqextension.Constants;
import de.hsmainz.cs.semgis.arqextension.TransformCache;
import org.apache.jena.datatypes.BaseDatatype;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

/**
 * Base literal type. The lexical form is parsed. All geometries are normalized
 * to WGS84 internally. For output, they are converted back to the original
 * representation.
 *
 * @author rbattle
 */
public abstract class GeoSPARQLLiteral extends BaseDatatype {

    public static final String DEFAULT_CRS_URI = "http://www.opengis.net/def/crs/OGC/1.3/CRS84";

    public static CoordinateReferenceSystem DEFAULT_CRS;

    // set the default coordinate reference system
    static {
        try {
            DEFAULT_CRS = CRS.decode(Constants.DEFAULT_CRS);
        } catch (NoSuchAuthorityCodeException e) {
            throw new RuntimeException("Could not find CRS for default CRS");
        } catch (FactoryException e) {
            throw new RuntimeException("Could not find CRS for default CRS");
        }
    }

    /**
     * A cache for transformations
     */
    protected static final TransformCache CACHE = new TransformCache();

    /**
     * Get a coordinate reference system URI by it's code.
     *
     * @param code the code (e.g., EPSG:4326)
     * @return a URI.
     */
    protected static final String getCoordinateReferenceSystemURI(String code) {
        if (null == code) {
            return null;
        }
        String auth = null;
        String prefix = null;
        if (code.contains("CRS")) {
            auth = "CRS" + code.substring(4);
            prefix = Constants.OGC_SRS_NS;
        } else if (code.contains("EPSG")) {
            auth = code.substring(5);
            prefix = Constants.EPSG_SRS_NS;
        } else {
            return null;
        }
        return prefix + auth;
    }

    /**
     * Get a coordinate reference system code by it's URI. Currently this only
     * supports URIs within the {@link Constants#OGC_SRS_NS} and
     * {@link Constants#EPSG_SRS_NS} namespaces.
     *
     * @param uri the URI of the CRS.
     * @return a code.
     */
    public static final String getCoordinateReferenceSystemCode(String uri) {
        if (null == uri) {
            return null;
        }
        String auth = null;
        if (uri.startsWith(Constants.OGC_SRS_NS)) {
            String id = uri.substring(Constants.OGC_SRS_NS.length());
            if (id.contains("CRS")) {
                auth = "CRS:" + id.substring(3);
            } else {
                auth = "CRS:" + id;
            }
        } else if (uri.startsWith(Constants.EPSG_SRS_NS)) {
            String id = uri.substring(Constants.EPSG_SRS_NS.length());
            auth = "EPSG:" + id;
        } else {
            return null;
        }
        return auth;
    }

    /**
     * Convert a geometry from one CRS to another.
     *
     * @param geometry the geometry to convert.
     * @param source the source CRS.
     * @param destination the destination CRS.
     * @return the converted geometry.
     */
    public static final Geometry convert(Geometry geometry,
            CoordinateReferenceSystem source, CoordinateReferenceSystem destination) {
        if (source.equals(destination)) {
            return geometry;
        }
        try {
            MathTransform transform = CACHE.getTransform(source, destination);
            return JTS.transform(geometry, transform);
        } catch (MismatchedDimensionException e) {
            throw new DatatypeFormatException(
                    String.format("Could not convert {0} to {1}",
                            source.getName(),
                            destination.getName()));
        } catch (NoSuchAuthorityCodeException e) {
            throw new DatatypeFormatException(
                    String.format("Could not convert {0} to {1}",
                            source.getName(),
                            destination.getName()));
        } catch (FactoryException e) {
            throw new DatatypeFormatException(
                    String.format("Could not convert {0} to {1}",
                            source.getName(),
                            destination.getName()));
        } catch (TransformException e) {
            throw new DatatypeFormatException(
                    String.format("Could not convert {0} to {1}",
                            source.getName(),
                            destination.getName()));
        }
    }

    /**
     * Construct a new instance.
     *
     * @param uri the URI of the datatype.
     */
    public GeoSPARQLLiteral(String uri) {
        super(uri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Geometry parse(String lexicalForm)
            throws DatatypeFormatException {
        Geometry geometry = doParse(lexicalForm);
        //      if (!geometry.isValid()) {
        //         int srid = geometry.getSRID();
        //         Object userData = geometry.getUserData();
        //         geometry = geometry.buffer(0);
        //         geometry.setSRID(srid);
        //         geometry.setUserData(userData);
        //
        ////         throw new DatatypeFormatException(lexicalForm, this, "The specified geometry is invalid");
        //      }
        geometry = normalize(geometry);
        return geometry;
    }

    /**
     * Parse the lexical form of the literal.
     *
     * @param lexicalForm the lexical form.
     * @return the geometry representation.
     * @throws DatatypeFormatException if the lexical form is not well formed.
     */
    protected abstract Geometry doParse(String lexicalForm)
            throws DatatypeFormatException;

    /**
     * {@inheritDoc}
     */
    @Override
    public final String unparse(Object value) {
        if (null == value) {
            return null;
        } else if (value instanceof Geometry) {
            Geometry g = denormalize((Geometry) value);
            return doUnparse(g);
        }
        return super.unparse(value);
    }

    /**
     * Unparse the given geometry into it's lexical form.
     *
     * @return the lexical form of the geometry
     */
    protected abstract String doUnparse(Geometry geometry);

    /**
     * Convert from WGS84 into the original CRS.
     *
     * @param geometry the geometry to convert.
     * @return the geometry in it's original CRS.
     */
    protected final Geometry denormalize(Geometry geometry) {
        Geometry value = geometry;

        String crs = geometry.getUserData().toString();
        try {
            CoordinateReferenceSystem source;
            CoordinateReferenceSystem destination;

            source = CRS.decode(Constants.INTERNAL_CRS);
            destination = CRS.decode(crs);

            return convert(value, source, destination);
        } catch (NoSuchAuthorityCodeException e) {
            throw new DatatypeFormatException(
                    String.format("Could not convert %s to %s",
                            Constants.INTERNAL_CRS,
                            crs));
        } catch (FactoryException e) {
            throw new DatatypeFormatException(
                    String.format("Could not convert %s to %s",
                            Constants.INTERNAL_CRS,
                            crs));
        }
    }

    /**
     * Normalize a geometry to WGS84.
     *
     * @param geometry the geometry to convert.
     * @return the geometry in the WGS84 CRS.
     */
    protected final Geometry normalize(Geometry geometry)
            throws DatatypeFormatException {
        Geometry value = geometry;

        String crs = geometry.getUserData().toString();
        try {
            CoordinateReferenceSystem source;
            CoordinateReferenceSystem destination;

            source = CRS.decode(crs);
            destination = CRS.decode(Constants.INTERNAL_CRS);
            return convert(value, source, destination);
        } catch (NoSuchAuthorityCodeException e) {
            throw new DatatypeFormatException(
                    String.format("Could not convert %s to %s",
                            crs,
                            Constants.INTERNAL_CRS));
        } catch (FactoryException e) {
            throw new DatatypeFormatException(
                    String.format("Could not convert %s to %s",
                            crs,
                            Constants.INTERNAL_CRS));
        }
    }
}
