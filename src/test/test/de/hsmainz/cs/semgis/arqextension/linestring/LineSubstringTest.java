package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.LineFromText;
import de.hsmainz.cs.semgis.arqextension.linestring.LineSubstring;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LineSubstringTest {	
	public static final String testLineString="LINESTRING(1 2, 3 4, 5 6)";
	
	public static final String testLineString2="LINESTRING(25 50, 100 125, 150 190)";
	
	@Test
	public void testLineSubstring() {
        LineSubstring instance=new LineSubstring();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1.,2.));
        coords.add(new Coordinate(3.,4.));
        coords.add(new Coordinate(5.,6.));
        coords.add(new Coordinate(7.,8.));
        List<Coordinate> coords2=new LinkedList<Coordinate>();
        coords2.add(new Coordinate(3.,4.));
        coords2.add(new Coordinate(5.,6.));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords2, WKTDatatype.URI).asNodeValue();
        NodeValue input = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(input,NodeValue.makeDouble(0.25),NodeValue.makeDouble(0.25));
        assertEquals(expResult, result);
	}

	@Test
	public void testLineSubstring2() {
        LineSubstring instance=new LineSubstring();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(25,50));
        coords.add(new Coordinate(100,125));
        coords.add(new Coordinate(150,190));
        coords.add(new Coordinate(7.,8.));
        List<Coordinate> coords2=new LinkedList<Coordinate>();
        coords2.add(new Coordinate(69.2846934853974,94.2846934853974));
        coords2.add(new Coordinate(100,125));
        coords2.add(new Coordinate(111.700356260683,140.210463138888));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords2, WKTDatatype.URI).asNodeValue();
        NodeValue input = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(input,NodeValue.makeDouble(0.3333),NodeValue.makeDouble(0.3333));
        assertEquals(expResult, result);
	}

}
