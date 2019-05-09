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

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

import java.awt.geom.Point2D;
import java.util.List;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoordinates2D;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.opengis.referencing.operation.TransformException;

/**
 * Returns the upper left Y coordinate of raster in projected spatial ref.
 *
 */
public class UpperLeftY extends Raster2DGeometrySpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        try {
            Point2D position = raster.getGridGeometry().getGridToCRS2D().transform(new GridCoordinates2D(0, 0),null);
            return NodeValue.makeDouble(position.getY());
        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{};
    }

}
