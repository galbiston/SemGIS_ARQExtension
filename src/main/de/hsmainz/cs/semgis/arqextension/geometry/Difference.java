package de.hsmainz.cs.semgis.arqextension.geometry;

import java.awt.geom.Rectangle2D;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class Difference extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			GeometryWrapper transGeom2;
			try {
				transGeom2 = ((GeometryWrapper)wrapper2).transform(((GeometryWrapper)wrapper1).getSrsInfo());
				return GeometryWrapperFactory.createGeometry(((GeometryWrapper)wrapper1).difference(transGeom2).getParsingGeometry(), ((GeometryWrapper)wrapper1).getSrsURI(), ((GeometryWrapper)wrapper1).getGeometryDatatypeURI()).asNodeValue();

			} catch (MismatchedDimensionException | TransformException | FactoryException e) {
				// TODO Auto-generated catch block
				return NodeValue.makeString(e.getMessage());
			}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getXYGeometry();		
	        Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
	        Rectangle2D bbox2 = raster2.getEnvelope2D().getBounds2D();
	        return GeometryWrapperFactory.createGeometry(LiteralUtils.toGeometry(bbox1.getBounds()).difference(LiteralUtils.toGeometry(bbox2.getBounds())),((GeometryWrapper)wrapper1).getGeometryDatatypeURI()).asNodeValue();			
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
				Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return GeometryWrapperFactory.createGeometry(LiteralUtils.toGeometry(bbox1.getBounds()).difference(geom),((CoverageWrapper) wrapper1).getSrsURI()).asNodeValue();
			}else {
				GridCoverage2D raster=((CoverageWrapper)wrapper2).getXYGeometry();
				Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return GeometryWrapperFactory.createGeometry(geom.difference(LiteralUtils.toGeometry(bbox1.getBounds())),((CoverageWrapper) wrapper1).getSrsURI()).asNodeValue();				
			}
		}
	}

}
