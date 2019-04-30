package de.hsmainz.cs.semgis.arqextension.envelope;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Envelope;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class ZMin extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Envelope envelope = geometry.getEnvelope();
            return NodeValue.makeDouble(envelope.get);
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }
}
