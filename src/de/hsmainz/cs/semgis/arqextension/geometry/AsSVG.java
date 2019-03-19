package de.hsmainz.cs.semgis.arqextension.geometry;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.io.WKBWriter;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AsSVG extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            SVGWriter writer=new SVGWriter();
            String endianness = arg1.getString();
            WKBWriter writer=new WKBWriter();
            
            byte[] result=writer.write(geometry.getParsingGeometry());
            if(arg1!=null) {
                ByteBuffer bb = ByteBuffer.wrap(result);
            	if("XDR".equals(endianness)) {
                    bb.order( ByteOrder.BIG_ENDIAN);
            	}else if("NDR".equals(endianness)) {
            		bb.order( ByteOrder.LITTLE_ENDIAN);
            	}
                return NodeValue.makeString(bb.toString());
            }
            return NodeValue.makeString(result.toString());
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
