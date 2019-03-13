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
/**
 *
 */
package de.hsmainz.cs.semgis.arqextension.raster;

import de.hsmainz.cs.semgis.arqextension.datatypes.GeoSPARQLLiteral;
import java.awt.image.RenderedImage;
import java.util.LinkedList;
import java.util.List;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.jena.vocabulary.XSD;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridCoverageFactory;
import org.geotools.coverage.processing.CoverageProcessor;
import org.geotools.coverage.util.IntersectUtils;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.opengis.coverage.Coverage;
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

/**
 * @author rbattle
 *
 */
public class Clip extends Raster2DGeometrySpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, Geometry g, GeoSPARQLLiteral datatype, Binding binding,
            List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        return makeNodeValueRaster(this.clipImageToFeatureSource(raster, (ReferencedEnvelope) raster.getEnvelope(), g), datatype);
    }

    private Coverage clipImageToFeatureSource(GridCoverage gridcoverage,
            ReferencedEnvelope bounds,
            Geometry geometry) {
        RenderedImage image = gridcoverage.getRenderedImage();
        CoordinateReferenceSystem crsFeatures;
        try {
            crsFeatures = CRS.decode("EPSG:" + geometry.getSRID());

            CoordinateReferenceSystem crsMap = bounds.getCoordinateReferenceSystem();
            boolean needsReproject = !CRS.equalsIgnoreMetadata(crsFeatures, crsMap);
            List<Geometry> all = new LinkedList<>();
            MathTransform transform = CRS.findMathTransform(crsFeatures, crsMap, true);

            if (geometry == null) {
                return gridcoverage;
            }
            if (!geometry.isSimple()) {
                return gridcoverage;
            }
            if (needsReproject) {
                geometry = JTS.transform(geometry, transform);
                System.out.println("Reprojected a geometry.  Result is " + geometry.toString());
            }
            Geometry intersection = geometry.intersection(JTS.toGeometry(bounds));
            if (intersection.isEmpty()) {
                return gridcoverage;
            }
            //String name = (String) feature.getAttribute("NAME");
            //if (name == null)
            //   name = (String) feature.getAttribute("CNTRY_NAME");
            if (intersection instanceof MultiPolygon) {
                MultiPolygon mp = (MultiPolygon) intersection;
                for (int i = 0; i < mp.getNumGeometries(); i++) {
                    org.locationtech.jts.geom.Polygon g = (org.locationtech.jts.geom.Polygon) mp.getGeometryN(i);
                    Geometry gIntersection = IntersectUtils.intersection(g, JTS.toGeometry(bounds));
                    if (gIntersection.isEmpty()) {
                        continue;
                    }
                    all.add(g);
                }
            } else if (intersection instanceof org.locationtech.jts.geom.Polygon) {
                all.add(intersection);
            }
            GridCoverageFactory gridCoverageFactory = new GridCoverageFactory();
            Coverage coverage = gridCoverageFactory.create("Raster", image, bounds);
            Coverage clippedCoverage = null;
            if (all.size() > 0) {
                CoverageProcessor processor = new CoverageProcessor();
                ParameterValueGroup params = processor.getOperation("CoverageCrop")
                        .getParameters();
                params.parameter("Source").setValue(coverage);
                GeometryFactory factory = JTSFactoryFinder.getGeometryFactory(null);
                Geometry[] a = all.toArray(new Geometry[0]);
                GeometryCollection c = new GeometryCollection(a, factory);
                //params.parameter("ENVELOPE").setValue(bounds);
                params.parameter("ROI").setValue(c);
                params.parameter("ForceMosaic").setValue(true);
                clippedCoverage = processor.doOperation(params);
            }
            if (all.isEmpty()) {
                System.out.println("Crop by shapefile requested but no simple features matched extent!");
            }
            return clippedCoverage;
        } catch (FactoryException | MismatchedDimensionException | TransformException e) {
            // TODO Auto-generated catch block
            return gridcoverage;
        }
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{XSD.xboolean.getURI()};
    }

}
