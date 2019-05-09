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
package de.hsmainz.cs.semgis.arqextension.linestring;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.util.PolygonExtracter;
import org.locationtech.jts.operation.linemerge.LineMerger;
import org.locationtech.jts.operation.polygonize.Polygonizer;

public class LineMerge extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            if (geom instanceof MultiLineString) {
                LineMerger merger = new LineMerger();
                merger.add(geom);
                //GeometryWrapper mergerWrapper = GeometryWrapperFactory.createGeometry(merger.getMergedLineStrings(), geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
                //return mergerWrapper.asNodeValue();
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            }

            return NodeValue.nvNothing;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
