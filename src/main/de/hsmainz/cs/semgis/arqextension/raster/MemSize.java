package de.hsmainz.cs.semgis.arqextension.raster;

import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns the amount of space (in bytes) the raster takes.
 *
 */
public class MemSize extends RasterSpatialFunction {

	@Override
	protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding,
			List<NodeValue> evalArgs, String uri, FunctionEnv env) {
		return NodeValue.makeInteger(raster.getRenderedImage().getData().getDataBuffer().getSize());
	}

	@Override
	protected String[] getRestOfArgumentTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
