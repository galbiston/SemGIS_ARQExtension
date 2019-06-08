package io.github.galbiston.geosparql_jena.implementation.datatype;

import io.github.galbiston.geosparql_jena.implementation.DimensionInfo;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.parsers.gml.GMLReader;
import io.github.galbiston.geosparql_jena.implementation.parsers.gml.GMLWriter;
import io.github.galbiston.geosparql_jena.implementation.vocabulary.Geo;
import java.io.IOException;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.geotoolkit.data.kml.xml.KmlReader;
import org.jdom2.JDOMException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.kml.KMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 *
 */
public class KMLDatatype extends GeometryDatatype {

    private static final Logger LOGGER = LoggerFactory.getLogger(KMLDatatype.class);

    /**
     * The default GML type URI.
     */
    public static final String URI = Geo.GML;

    /**
     * A static instance of GMLDatatype.
     */
    public static final KMLDatatype INSTANCE = new KMLDatatype();

    /**
     * XML element tag "gml" is defined for the convenience of GML generation.
     */
    public static final String KML_PREFIX = "kml";

    /**
     * private constructor - single global instance.
     */
    public KMLDatatype() {
        super(URI);
    }

    /**
     * This method Un-parses the JTS Geometry to the GML literal
     *
     * @param geometry - the JTS Geometry to be un-parsed
     * @return GML - the returned GML Literal.
     * <br> Notice that the Spatial Reference System
     * "urn:ogc:def:crs:OGC::CRS84" is predefined in the returned GML literal.
     */
    @Override
    public String unparse(Object geometry) {
        if (geometry instanceof GeometryWrapper) {
            GeometryWrapper geometryWrapper = (GeometryWrapper) geometry;
            KMLWriter writer=new KMLWriter();
            return writer.write(geometryWrapper.getXYGeometry());
        } else {
            throw new AssertionError("Object passed to KMLDatatype is not a GeometryWrapper: " + geometry);
        }
    }

    @Override
    public GeometryWrapper read(String geometryLiteral) {
        /*try {
            KmlReader kmlReader = KmlReader(geometryLiteral);
            kmlReader.
            kmlReader.rea.extract(geometryLiteral);
            Geometry geometry = kmlReader.getGeometry();
            DimensionInfo dimensionInfo = kmlReader.getDimensionInfo();

            return new GeometryWrapper(geometry, kmlReader.getSrsName(), URI, dimensionInfo, geometryLiteral);
        } catch (JDOMException | IOException ex) {
            throw new DatatypeFormatException("Illegal KML literal:" + geometryLiteral + ". " + ex.getMessage());
        }*/
    	return null;
    }

    @Override
    public String toString() {
        return "KMLDatatype{" + URI + '}';
    }

}

