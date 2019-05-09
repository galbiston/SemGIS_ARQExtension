package de.hsmainz.cs.semgis.arqextension.raster;

import java.io.IOException;
import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.coverage.wkb.WKBRasterWriter;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class AsBinary extends RasterSpatialFunction{

		@Override
		protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding,
				List<NodeValue> evalArgs, String uri, FunctionEnv env) {
			WKBRasterWriter writer=new WKBRasterWriter();
			String rasterWKB;
			try {
				rasterWKB = writer.write(raster).toString();
				return NodeValue.makeString(rasterWKB.toString());
			} catch (IOException | FactoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}

		}

		@Override
		protected String[] getRestOfArgumentTypes() {
			// TODO Auto-generated method stub
			return null;
		}

}
