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
import org.apache.jena.sparql.function.FunctionBase3;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.operation.distance.DistanceOp;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;

public class DWithin extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2,NodeValue v3) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage2D raster=wrapper.getXYGeometry();
		CoverageWrapper wrapper2=CoverageWrapper.extract(v2);
		GridCoverage2D raster2=wrapper.getXYGeometry();
        Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
        Rectangle2D bbox2 = raster2.getEnvelope2D().getBounds2D();
        Double withinDistance = v3.getDouble();
        GeodeticCalculator calc = new GeodeticCalculator();
        Coordinate[] points = DistanceOp.nearestPoints(LiteralUtils.toGeometry(bbox1.getBounds()), LiteralUtils.toGeometry(bbox2.getBounds()));
        calc.setStartingGeographicPoint(points[0].x, points[0].y);
        calc.setDestinationGeographicPoint(points[1].x, points[1].y);
        Double actualdistance = calc.getOrthodromicDistance();
        return null;
	}

}
