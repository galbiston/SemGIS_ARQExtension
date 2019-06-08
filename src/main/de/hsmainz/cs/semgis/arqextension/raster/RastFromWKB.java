package de.hsmainz.cs.semgis.arqextension.raster;

import java.awt.image.BufferedImage;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.coverage.wkb.WKBRasterImageReader;
import org.geotoolkit.coverage.wkb.WKBRasterReader;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.vocabulary.Geo;

public class RastFromWKB extends FunctionBase1 {
	

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            String wkbstring=arg0.getString();
    		WKBRasterReader reader=new WKBRasterReader();
    		GridCoverage2D coverage=reader.readCoverage(wkbstring.getBytes(),null);
            if("POLYGON".equals(geom.getGeometryType().toUpperCase())){
            	GeometryWrapper pointWrapper = GeometryWrapperFactory.createGeometry(geom, "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", WKTDatatype.URI);	
                return pointWrapper.asNodeValue();
            }else {
            	throw new ExprEvalException("WKB does not represent a polygon", null);
            }
            
        } catch (DatatypeFormatException | ParseException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}


}
