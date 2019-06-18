/** *****************************************************************************
 * Copyright (c) 2017 Timo Homburg, i3Mainz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the BSD License
 * which accompanies this distribution, and is available at
 * https://directory.fsf.org/wiki/License:BSD_4Clause
 *
 * This project extends work by Ian Simmons who developed the Parliament Triple Store.
 * http://parliament.semwebcentral.org and published his work und BSD License as well.
 *
 *
 ****************************************************************************** */
package de.hsmainz.cs.semgis.arqextension.raster;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

import java.awt.geom.Rectangle2D;
import java.util.List;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;


public class SRID extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v);
		if(wrapper1 instanceof GeometryWrapper) {
			return NodeValue.makeString(((GeometryWrapper)wrapper1).getXYGeometry().getSRID()+"");
		}else {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
	        return NodeValue.makeString(raster.getCoordinateReferenceSystem().getCoordinateSystem().getName().toString());			
		}
	}

}
