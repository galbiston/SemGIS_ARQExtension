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

import java.util.List;

import org.geotoolkit.coverage.grid.GridCoordinates2D;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.opengis.referencing.operation.TransformException;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.sis.geometry.DirectPosition2D;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns the column in the raster of the point geometry (pt) or a X and Y world coordinate (xw, yw) represented in world spatial reference system of raster.
 *
 */
public class WorldToRasterCoordX extends Raster2DGeometrySpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        Integer longitude = evalArgs.get(0).getInteger().intValue();
        Integer latitude = evalArgs.get(1).getInteger().intValue();
        try {
            GridCoordinates2D position = raster.getGridGeometry().worldToGrid(new DirectPosition2D(longitude, latitude));
            return NodeValue.makeDouble(position.getX());
        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        return new String[]{};
    }

}
