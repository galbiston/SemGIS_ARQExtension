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

/**
 * Returns true if there is no band with given band number. If no band number is specified, then band number 1 is assumed.
 *
 */
public class HasNoBand extends RasterSpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding,
            List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        Integer bandNumber;
        if (evalArgs.isEmpty()) {
            bandNumber = 1;
        }
        bandNumber = evalArgs.get(0).getInteger().intValue();
        if (bandNumber > raster.getNumSampleDimensions()) {
            return NodeValue.FALSE;
        }
        return NodeValue.TRUE;
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        return new String[]{XSD.xint.getURI()};
    }

}
