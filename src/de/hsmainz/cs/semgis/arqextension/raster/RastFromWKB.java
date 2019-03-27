package de.hsmainz.cs.semgis.arqextension.raster;

import java.awt.image.BufferedImage;
import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.coverage.wkb.WKBRasterImageReader;
import org.geotoolkit.coverage.wkb.WKBRasterReader;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.vocabulary.Geo;

public class RastFromWKB extends RasterSpatialFunction{
	
	@Override
	protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding,
			List<NodeValue> evalArgs, String uri, FunctionEnv env) {
		WKBRasterReader reader2=new WKBRasterReader();
		GridCoverage2D coverage=reader2.readCoverage(data, authorityFactory);
	}

	@Override
	protected String[] getRestOfArgumentTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
