package de.hsmainz.cs.semgis.arqextension.linestring;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineSegment;
import org.locationtech.jts.geom.LineString;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Return a linestring being a substring of the input one starting and ending at the given fractions of total 2d length. 
 *
 */
public class LineSubstring extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
		Double startfraction=v2.getDouble();
		Double endfraction=v3.getDouble();
		Double totallength=geom1.getXYGeometry().getLength();
		Double startleaveout=startfraction*totallength;
		Double endleaveout=endfraction*totallength;
		List<LineString> res=splitLineStringIntoParts((LineString)geom1.getXYGeometry(), new double[] {startleaveout,totallength-startleaveout-endleaveout,endleaveout});
    	System.out.println(res);
		return GeometryWrapperFactory.createLineString(res.get(1).getCoordinates(), WKTDatatype.URI).asNodeValue();
	}
	
	public ArrayList<LineString> splitLineStringIntoParts(LineString ls, double[] length){
	    // result list for linestrings
	    ArrayList<LineString> resultList = new ArrayList();
	    // list for linesegments from input linestring
	    ArrayList<LineSegment> lineSegmentList = new ArrayList();
	    // create LineSegment objects from input linestring and add them to list
	    for(int i = 1; i < ls.getCoordinates().length; i++){
	        lineSegmentList.add(new LineSegment(ls.getCoordinates()[i-1], ls.getCoordinates()[i]));
	    }
	    LineString currentLineString = null;
	    double neededLength = length[0];
	    Integer lengthcounter=0;
	    for(LineSegment s : lineSegmentList){
	        while(s.getLength() > 0){
	            // case: current segment is small enough to be added to the linestring
	            if(s.getLength() <= neededLength){
	                // create linestring if it does not exist 
	                if(currentLineString == null){
	                    currentLineString = new GeometryFactory().createLineString(new Coordinate[]{new Coordinate(s.p0), new Coordinate(s.p1)});
	                // just add the new endpoint otherwise
	                } else {
	                    Coordinate[] coords = new Coordinate[currentLineString.getCoordinates().length + 1];
	                    // copy old coordinates
	                    System.arraycopy(currentLineString.getCoordinates(), 0, coords, 0, currentLineString.getCoordinates().length);
	                    // add new coordinate at the end
	                    coords[coords.length-1] = new Coordinate(s.p1);
	                    // create new linestring
	                    currentLineString = new GeometryFactory().createLineString(coords);
	                }
	                neededLength -= s.getLength();
	                s.setCoordinates(s.p1, s.p1);
	                // add linestring to result list if needed length is 0
	                if(neededLength == 0){
	                    resultList.add(currentLineString);
	                    currentLineString = null;
	                    neededLength = length[lengthcounter++];
	                }
	            // current segment needs to be cut and added to the linestring
	            } else {
	                // get coordinate at desired distance (endpoint of linestring)
	                Coordinate endPoint = s.pointAlong(neededLength/s.getLength());
	                // create linestring if it does not exist 
	                if(currentLineString == null){
	                    currentLineString = new GeometryFactory().createLineString(new Coordinate[]{new Coordinate(s.p0), endPoint});
	                // just add the new endpoint otherwise
	                } else {
	                    // add new coordinate to linestring
	                    Coordinate[] coords = new Coordinate[currentLineString.getCoordinates().length + 1];
	                    // copy old coordinates
	                    System.arraycopy(currentLineString.getCoordinates(), 0, coords, 0, currentLineString.getCoordinates().length);
	                    // add new coordinate at the end
	                    coords[coords.length-1] = endPoint;
	                    currentLineString = new GeometryFactory().createLineString(coords);
	                }
	                // add linestring to result list
	                resultList.add(currentLineString);
	                // reset needed length
	                neededLength = length[lengthcounter++];
	                // reset current linestring
	                currentLineString = null;
	                // adjust segment (calculated endpoint is the new startpoint)
	                s.setCoordinates(endPoint, s.p1);
	            }
	        }
	    }
	    // add last linestring if there is a rest
	    if(neededLength < length[lengthcounter]){
	        resultList.add(currentLineString);
	    }
	    return resultList;
	}
	

}
