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
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.opengis.coverage.grid.GridCoverage;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.jena.vocabulary.XSD;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Value extends Raster2DGeometrySpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        Integer bandnum = evalArgs.get(0).getInteger().intValue();
        Integer column = evalArgs.get(1).getInteger().intValue();
        Integer row = evalArgs.get(2).getInteger().intValue();
        Double d = ((double[]) raster.getRenderedImage().getData().getDataElements(column, row, new double[]{}))[0];
        return NodeValue.makeDouble(d);
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{XSD.xint.getURI(), XSD.xint.getURI(), XSD.xint.getURI()};
    }

}
