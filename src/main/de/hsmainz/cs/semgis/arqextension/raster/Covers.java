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
import java.awt.geom.Rectangle2D;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.geometry.jts.JTS;

public class Covers extends FunctionBase2 {
	
	
	@Override
	public NodeValue exec(NodeValue v,NodeValue v1) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		CoverageWrapper wrapper2=CoverageWrapper.extract(v1);
		GridCoverage2D raster=wrapper.getXYGeometry();
		GridCoverage2D raster2=wrapper2.getXYGeometry();		
        Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
        Rectangle2D bbox2 = raster2.getEnvelope2D().getBounds2D();
        return NodeValue.makeBoolean(JTS.toGeometry(bbox1.getBounds()).covers(JTS.toGeometry(bbox2.getBounds())));
	}

}
