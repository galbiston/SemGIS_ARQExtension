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
import org.locationtech.jts.geom.CoordinateXY;
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.operation.TransformException;

public class RasterToWorldCoord extends RasterSpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage raster, GeometryWrapper geometryWrapper, Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        Integer column = evalArgs.get(0).getInteger().intValue();
        Integer row = evalArgs.get(1).getInteger().intValue();

        try {
            DirectPosition position = raster.getGridGeometry().gridToWorld(new org.geotoolkit.coverage.grid.GridCoordinates2D(column, row));
            CoordinateXY coord = new CoordinateXY(position.getCoordinate()[0], position.getCoordinate()[1]);
            GeometryWrapper pointWrapper = GeometryWrapper.createPoint(coord, geometryWrapper.getSrsURI(), geometryWrapper.getGeometryDatatypeURI());
            return pointWrapper.asNodeValue();
        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        return new String[]{XSD.xint.getURI(), XSD.xint.getURI()};
    }

}
