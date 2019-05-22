package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.ShiftLongitude;
import de.hsmainz.cs.semgis.arqextension.geometry.ShortestLine;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ShiftLongitudeTest {

	public static final String testPoint="POINT(-118.58 38.38 10)";
	
	public static final String shiftedTestPoint="POINT(-118.58 38.38 10)";
	
	public static final String testLineString="LINESTRING(-118.58 38.38, -118.20 38.45)";

	public static final String shiftedTestLineString="LINESTRING(241.42 38.38,241.8 38.45)";
	
	
	@Test
	public void testShiftLongitude() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
        ShiftLongitude instance=new ShiftLongitude();
        NodeValue expResult = NodeValue.makeNode(shiftedTestPoint, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testShiftLongitude2() {
        NodeValue geometryLiteral2 = NodeValue.makeNode(testLineString, WKTDatatype.INSTANCE); 
        ShiftLongitude instance=new ShiftLongitude();
        NodeValue expResult = NodeValue.makeNode(shiftedTestLineString, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral2);
        assertEquals(expResult, result);
	}

}