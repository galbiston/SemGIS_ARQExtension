package de.hsmainz.cs.semgis.arqextension.linestring;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LineFromEncodedPolyline extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v,NodeValue v2) {
		 String polyline=v.asString();
		 BigInteger precision=v2.getInteger();
    	GeometryWrapper pointWrapper = GeometryWrapper.createLineString(decodePolyline(polyline, precision.intValue()), "<http://www.opengis.net/def/crs/EPSG/0/4326>", WKTDatatype.URI);	
        return pointWrapper.asNodeValue();
	}
	
	public static List<Coordinate> decodePolyline(String polyline, int precision)
    {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        int index = 0, shift, result;
        int byte_;
        int lat = 0, lng = 0, latitude_change, longitude_change,
            factor = (int) Math.pow(10, precision);

        while (index < polyline.length()) {
            byte_ = 0;
            shift = 0;
            result = 0;

            do {
                byte_ = polyline.charAt(index++) - 63;
                result |= (byte_ & 0x1f) << shift;
                shift += 5;
            } while (byte_ >= 0x20);

            latitude_change = ((result % 2 == 1) ? ~(result >> 1) : (result >> 1));

            shift = result = 0;

            do {
                byte_ = polyline.charAt(index++) - 63;
                result |= (byte_ & 0x1f) << shift;
                shift += 5;
            } while (byte_ >= 0x20);

            longitude_change = ((result % 2 == 1) ? ~(result >> 1) : (result >> 1));

            lat += latitude_change;
            lng += longitude_change;

            coordinates.add(new Coordinate((double)lat / factor,(double)lng / factor));
        }

        return coordinates;
    }

	
	/*public static void main(String[] args) {
		String polyline = "onynWggt_xCga@~EgxBzO}fA`BCoE|EIz]i@t`@m@vwByOfs@iJj{B_a@zcDig@jmCgSva@sDdhDs]pg@qMneAiXz`AwZ~VeLpn@eYdm@eUpKsAbKeAxx@oB`r@OfGx@`LxAlc@zFjZ~Ff{A~Y|WxFr_@rA~bAIneAKzFbAxZsLdlAmIdgAmKzUoFjwAyV~SkGtY{ItMkDxVgHhH_AtP}BhRXbXd@xFJlIZzQpGl_@vPpPfLjFhHJtHB`R?vXIdC"; //This is an OSRM generated polyline
        List<Coordinate> pointList = LineFromEncodedPolyline.decodePolyline(polyline, 6); //Use 5 for google maps polyline
        // pointList contains the list of all Geocodes in sequence in the route
        for (Coordinate G: pointList) {
            System.out.print("[" + G.x + "," + G.y +"],");
        }
        System.out.println();
	}*/

}
