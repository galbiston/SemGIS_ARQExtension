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
import java.util.List;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;

public abstract class DoubleRaster2DSpatialFunction extends SpatialFunctionBase {

    /**
     * {@inheritDoc}
     */
    @Override
    protected NodeValue exec(Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        NodeValue geom = evalArgs.get(0);
        GridCoverage2D raster = (GridCoverage2D) geom.getNode().getLiteralValue();
        NodeValue geom2 = evalArgs.get(0);
        GridCoverage2D raster2 = (GridCoverage2D) geom2.getNode().getLiteralValue();
        GeometryWrapper geometryWrapper = GeometryWrapper.extract(geom);
        return exec(raster, raster2, geometryWrapper, binding, evalArgs, uri, env);
    }

    protected abstract NodeValue exec(GridCoverage2D raster, GridCoverage2D raster2, GeometryWrapper geometryWrapper, Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env);

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getArgumentTypes() {
        String[] rest = getRestOfArgumentTypes();
        String[] args = new String[rest.length + 1];
        args[0] = "ogc:GeomLiteral";
        int i = 1;
        for (String s : rest) {
            args[i++] = s;
        }
        return args;
    }

}
