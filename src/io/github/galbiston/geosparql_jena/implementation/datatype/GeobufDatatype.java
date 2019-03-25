package io.github.galbiston.geosparql_jena.implementation.datatype;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conveyal.data.geobuf.GeobufDecoder;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.vocabulary.SRS_URI;

public class GeobufDatatype extends GeometryDatatype  {

	    private static final Logger LOGGER = LoggerFactory.getLogger(WKTDatatype.class);

	    /**
	     * The default WKT type URI.
	     */
	    public static final String URI = PostGISGeo.WKB;

	    /**
	     * A static instance of WKTDatatype.
	     */
	    public static final GeobufDatatype INSTANCE = new GeobufDatatype();

	    /**
	     * private constructor - single global instance.
	     */
	    private GeobufDatatype() {
	        super(URI);
	    }

	    /**
	     * This method Un-parses the JTS Geometry to the WKT literal
	     *
	     * @param geometry - the JTS Geometry to be un-parsed
	     * @return WKT - the returned WKT Literal.
	     * <br> Notice that the Spatial Reference System is not specified in
	     * returned WKT literal.
	     *
	     */
	    @Override
	    public String unparse(Object geometry) {

	        if (geometry instanceof GeometryWrapper) {
	            GeometryWrapper geometryWrapper = (GeometryWrapper) geometry;
	            WKBWriter writer=new WKBWriter();
	            return writer.write(geometryWrapper.getXYGeometry()).toString();
	        } else {
	            throw new AssertionError("Object passed to WKBDatatype is not a GeometryWrapper: " + geometry);
	        }
	    }

	    @Override
	    public GeometryWrapper read(String geometryLiteral) {
	    	InputStream stream = new ByteArrayInputStream(geometryLiteral.getBytes(StandardCharsets.UTF_8));
	    	GeobufDecoder decoder;
			try {
				decoder = new GeobufDecoder(stream);
		    	Geometry geom=decoder.next().geometry;
			    GeometryWrapper wrapper = GeometryWrapper.createGeometry(geom, "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", WKTDatatype.URI);	
			    return wrapper;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}
	    }


	    @Override
	    public String toString() {
	        return "GeoBufDatatype{" + URI + '}';
	    }

}

