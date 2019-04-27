package de.hsmainz.cs.semgis.arqextension.raster;

import java.io.IOException;
import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.image.io.SpatialImageWriteParam;
import org.geotoolkit.image.io.plugin.TiffImageWriter;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AsTIFF extends RasterSpatialFunction{

	@Override
	protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding,
			List<NodeValue> evalArgs, String uri, FunctionEnv env) {
		TiffImageWriter writer=new TiffImageWriter(null);
		SpatialImageWriteParam writerParam = writer.getDefaultWriteParam();
		String compression=null;
        /*if (compression != null) {
            writerParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            writerParam.setCompressionType(compression);
        }*/
		try {
			writer.write(raster.getRenderedImage());
			writer.endWriteSequence();
			return NodeValue.makeString(writer.getOutput().toString());
		} catch (IOException e) {
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
