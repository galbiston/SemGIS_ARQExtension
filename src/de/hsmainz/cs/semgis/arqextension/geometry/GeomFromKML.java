package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.data.kml.xml.KmlReader;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.parsers.wkt.WKTReader;

public class GeomFromKML  extends FunctionBase1{
	
	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            String wktstring=arg0.getString();
            KmlReader kmlreader=new KmlReader();
            kmlreader.
            kmlreader.read();
            WKTReader reader=new WKTReader();
            WKTReader wktreader=WKTReader.extract(wktstring);
            Geometry geom=wktreader.getGeometry();
            GeometryWrapper pointWrapper = GeometryWrapper.createGeometry(geom, "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", WKTDatatype.URI);	
            return pointWrapper.asNodeValue();           
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}


}
