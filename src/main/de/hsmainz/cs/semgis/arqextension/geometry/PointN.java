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
package de.hsmainz.cs.semgis.arqextension.geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import java.math.BigInteger;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.Geometry;

public class PointN extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();

            BigInteger n = arg1.getInteger();
            if (n.intValue() >= geom.getCoordinates().length) {
                return NodeValue.nvNothing;
            }
            Coordinate[] coords = geom.getCoordinates();
            CoordinateXY coord = new CoordinateXY(coords[n.intValue()].x, coords[n.intValue()].y);
            GeometryWrapper pointWrapper = GeometryWrapper.createPoint(coord, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());

            return pointWrapper.asNodeValue();

        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}