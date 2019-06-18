package de.hsmainz.cs.semgis.arqextension.raster;

import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
/**
 * Returns the X component of the pixel width in units of coordinate reference system.
 *
 */
public class ScaleX extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
