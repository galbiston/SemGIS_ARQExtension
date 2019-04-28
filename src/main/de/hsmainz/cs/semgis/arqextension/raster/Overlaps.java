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
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.opengis.coverage.grid.GridCoverage;

public class Overlaps extends DoubleRaster2DSpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GridCoverage2D raster2, GeometryWrapper geometryWrapper, Binding binding,
            List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        return NodeValue.makeBoolean(raster.getEnvelope2D().getBounds2D().intersects(raster2.getEnvelope2D().getBounds2D())
                && !raster.getEnvelope2D().getBounds2D().contains(raster2.getEnvelope2D().getBounds2D()));
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{};
    }

}
