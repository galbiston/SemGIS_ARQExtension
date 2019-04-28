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

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.jena.vocabulary.XSD;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.geometry.jts.JTS;
import org.geotoolkit.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.operation.distance.DistanceOp;

public class DWithin extends DoubleRaster2DSpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GridCoverage2D raster2, GeometryWrapper geometryWrapper, Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
        Rectangle2D bbox2 = raster2.getEnvelope2D().getBounds2D();
        Double withinDistance = evalArgs.get(0).getDouble();
        GeodeticCalculator calc = new GeodeticCalculator();
        Coordinate[] points = DistanceOp.nearestPoints(JTS.toGeometry(bbox1.getBounds()), JTS.toGeometry(bbox2.getBounds()));
        calc.setStartingGeographicPoint(points[0].x, points[0].y);
        calc.setDestinationGeographicPoint(points[1].x, points[1].y);
        Double actualdistance = calc.getOrthodromicDistance();
        return null;

    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        return new String[]{XSD.xdouble.getURI()};
    }

}
