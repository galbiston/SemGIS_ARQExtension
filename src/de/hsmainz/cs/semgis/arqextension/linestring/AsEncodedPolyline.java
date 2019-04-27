package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AsEncodedPolyline extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
		GeometryWrapper geometry = GeometryWrapper.extract(arg0);
        Geometry geom = geometry.getXYGeometry();

        if (geom instanceof LineString) {
        	String result=encode((LineString)geom);
        	return NodeValue.makeString(result);
        }else {
        	throw new ExprEvalException("Given input does not represent a LineString", null);
        }
	}
	
	/**
     * Encodes a sequence of LatLngs into an encoded path string.
     * Modified from https://gitlab.com/aceperry/androidmapsutil
     * Apache License 2.0
     */
    public static String encode(final LineString linestring) {
        long lastLat = 0;
        long lastLng = 0;

        final StringBuffer result = new StringBuffer();

        for (final Coordinate point : linestring.getCoordinates()) {
            long lat = Math.round(point.x * 1e5);
            long lng = Math.round(point.y * 1e5);

            long dLat = lat - lastLat;
            long dLng = lng - lastLng;

            encode(dLat, result);
            encode(dLng, result);

            lastLat = lat;
            lastLng = lng;
        }
        return result.toString();
    }

    private static void encode(long v, StringBuffer result) {
        v = v < 0 ? ~(v << 1) : v << 1;
        while (v >= 0x20) {
            result.append(Character.toChars((int) ((0x20 | (v & 0x1f)) + 63)));
            v >>= 5;
        }
        result.append(Character.toChars((int) (v + 63)));
    }

	
}
