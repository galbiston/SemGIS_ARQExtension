package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.point.Z;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ZTest {

	
	@Test
	public void testZWithoutZ() {
        NodeValue geometryLiteral = NodeValue.makeNode("<http://www.opengis.net/def/crs/EPSG/0/27700> POINT (30 10)", WKTDatatype.INSTANCE);
        Z instance=new Z();
        NodeValue expResult = NodeValue.makeInteger(0);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testZWithZ() {
        NodeValue geometryLiteral = NodeValue.makeNode("<http://www.opengis.net/def/crs/EPSG/0/27700> POINT (30 10 10)", WKTDatatype.INSTANCE);
        Z instance=new Z();
        NodeValue expResult = NodeValue.makeInteger(0);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
