package de.hsmainz.cs.semgis.arqextension.raster;

import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns a raster with the new band(s) of given type added with given initial value in the given index location. If no index is specified, the band is added to the end. 
 *
 */
public class AddBand extends RasterSpatialFunction {

	@Override
	protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding,
			List<NodeValue> evalArgs, String uri, FunctionEnv env) {
		return null;
	}

	@Override
	protected String[] getRestOfArgumentTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
