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
import java.util.List;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.jena.vocabulary.XSD;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.CoordinateXY;
import org.opengis.coverage.grid.GridCoordinates;
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.referencing.operation.TransformException;


/**
 * Returns the upper left corner as column and row given geometric X and Y (longitude and latitude) or a point geometry expressed in the spatial reference coordinate system of the raster.
 *
 */
public class WorldToRasterCoord extends Raster2DGeometrySpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding,
            List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        Integer longitude = evalArgs.get(0).getInteger().intValue();
        Integer latitude = evalArgs.get(1).getInteger().intValue();

        try {
            GridCoordinates position = raster.getGridGeometry().getworldToGrid(new org.apache.sis.geometry.DirectPosition2D(longitude, latitude));
            CoordinateXY coord = new CoordinateXY(position.getX(), position.getY());
            GeometryWrapper pointWrapper = GeometryWrapper.createPoint(coord, geometryWrapper.getSrsURI(), geometryWrapper.getGeometryDatatypeURI());
            return pointWrapper.asNodeValue();

        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{XSD.xint.getURI(), XSD.xint.getURI()};
    }

}
