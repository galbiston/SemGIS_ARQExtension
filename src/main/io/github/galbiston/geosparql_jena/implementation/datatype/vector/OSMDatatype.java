package io.github.galbiston.geosparql_jena.implementation.datatype.vector;

	import io.github.galbiston.geosparql_jena.implementation.DimensionInfo;
	import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.parsers.wkt.WKTReader;
	import io.github.galbiston.geosparql_jena.implementation.parsers.wkt.WKTWriter;
	import io.github.galbiston.geosparql_jena.implementation.vocabulary.Geo;
	import io.github.galbiston.geosparql_jena.implementation.vocabulary.SRS_URI;

import org.geotoolkit.data.osm.OSMMemoryFeatureStore;
import org.geotoolkit.data.osm.OSMMemoryFeatureStoreFactory;
import org.geotoolkit.data.osm.xml.OSMXMLWriter;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;
import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
import org.wololo.jts2geojson.GeoJSONReader;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;

	/**
	 * WKTDatatype class allows the URI "geo:wktLiteral" to be used as a datatype
	 * and it will parse that datatype to a JTS Geometry.
	 *
	 * Req 10 All RDFS Literals of type geo:wktLiteral shall consist of an optional
	 * URI identifying the coordinate reference system followed by Simple Features
	 * Well Known Text (WKT) describing a geometric value. Valid geo:wktLiterals are
	 * formed by concatenating a valid, absolute URI as defined in [RFC 2396], one
	 * or more spaces (Unicode U+0020 character) as a separator, and a WKT string as
	 * defined in Simple Features [ISO 19125-1].
	 *
	 * Req 11 The URI {@code <http://www.opengis.net/def/crs/OGC/1.3/CRS84>} shall
	 * be assumed as the spatial reference system for geo:wktLiterals that do not *
	 * specify an explicit spatial reference system URI.
	 */
	public class OSMDatatype extends GeometryDatatype {

	    private static final Logger LOGGER = LoggerFactory.getLogger(OSMDatatype.class);

	    /**
	     * The default WKT type URI.
	     */
	    public static final String URI = PostGISGeo.OSM;

	    /**
	     * A static instance of WKTDatatype.
	     */
	    public static final OSMDatatype INSTANCE = new OSMDatatype();

	    /**
	     * private constructor - single global instance.
	     */
	    private OSMDatatype() {
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
	    		OSMMemoryFeatureStoreFactory fac=new OSMMemoryFeatureStoreFactory();
	    		OSMMemoryFeatureStore osmfeat=(OSMMemoryFeatureStore) fac.createDataStore(new java.net.URI(""));
	    		OSMXMLWriter writer=new OSMXMLWriter();
	        if (geometry instanceof GeometryWrapper) {
	            GeometryWrapper geometryWrapper = (GeometryWrapper) geometry;
	            WKBWriter writerr=new WKBWriter();
	            return writerr.write(geometryWrapper.getXYGeometry()).toString();
	        } else {
	            throw new AssertionError("Object passed to WKBDatatype is not a GeometryWrapper: " + geometry);
	        }
	    }

	    @Override
	    public GeometryWrapper read(String geometryLiteral) {
	        WKBTextSRS wkbTextSRS = new WKBTextSRS(geometryLiteral);

	        WKBReader wkbReader = new WKBReader();
	        Geometry geometry;
			try {
				geometry = wkbReader.read(wkbTextSRS.getWkbText().getBytes());
		        GeometryWrapper wrapper = GeometryWrapper.createGeometry(geometry, "<http://www.opengis.net/def/crs/EPSG/0/"+geometry.getSRID()+">", WKBDatatype.URI);	
		        return wrapper;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}


	    }

	    private class WKBTextSRS {

	        private final String wkbText;
	        private final String srsURI;

	        public WKBTextSRS(String wkbLiteral) {
	            int startSRS = wkbLiteral.indexOf("<");
	            int endSRS = wkbLiteral.indexOf(">");

	            //Check that both chevrons are located and extract SRS_URI name, otherwise default.
	            if (startSRS != -1 && endSRS != -1) {
	                srsURI = wkbLiteral.substring(startSRS + 1, endSRS);
	                wkbText = wkbLiteral.substring(endSRS + 1);

	            } else {
	                srsURI = SRS_URI.DEFAULT_WKT_CRS84;
	                wkbText = wkbLiteral;
	            }
	        }

	        public String getWkbText() {
	            return wkbText;
	        }

	        public String getSrsURI() {
	            return srsURI;
	        }

	    }

	    @Override
	    public String toString() {
	        return "WKBDatatype{" + URI + '}';
	    }
	
}

