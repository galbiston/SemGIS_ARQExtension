package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.AsKML;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsKMLTest {

	public static final String testGeometry="POLYGON((0 0,0 1,1 1,1 0,0 0))";
	
	@Test
	public void testAsKML() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsKML instance=new AsKML();
        NodeValue expResult = NodeValue.makeString("<Polygon><outerBoundaryIs><LinearRing><coordinates>0,0 0,1 1,1 1,0 0,0</coordinates></LinearRing></outerBoundaryIs></Polygon>");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
