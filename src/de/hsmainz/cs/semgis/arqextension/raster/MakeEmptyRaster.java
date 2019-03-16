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
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridCoverageFactory;

public class MakeEmptyRaster extends RasterSpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding,
            List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        Integer width;
        Integer height;
        Double upperleftx;
        Double upperlefty;
        GridCoverageFactory factory = new GridCoverageFactory();
        if (raster != null) {
            //factory.
        } else {
            width = evalArgs.get(0).getInteger().intValue();
            height = evalArgs.get(1).getInteger().intValue();
            upperleftx = evalArgs.get(2).getDouble();
            upperlefty = evalArgs.get(3).getDouble();
        }
        return null;
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{XSD.xint.getURI(), XSD.xint.getURI(), XSD.xdouble.getURI(), XSD.xdouble.getURI(), XSD.xdouble.getURI()};
    }

}
