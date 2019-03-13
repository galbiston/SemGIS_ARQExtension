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

import de.hsmainz.cs.semgis.arqextension.datatypes.GeoSPARQLLiteral;
import java.util.List;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotools.coverage.grid.GridCoverage2D;

public class Height extends RasterSpatialFunction {

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{};
    }

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeoSPARQLLiteral datatype, Binding binding, List<NodeValue> evalArgs, String uri,
            FunctionEnv env) {
        return NodeValue.makeInteger(raster.getRenderedImage().getHeight());
    }

}
