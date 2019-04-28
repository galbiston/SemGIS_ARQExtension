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

public class Summary extends Raster2DGeometrySpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env) {
    	StringBuilder builder = new StringBuilder();
        builder.append("Raster of " + raster.getRenderedImage().getWidth() + "x" + raster.getRenderedImage().getHeight() + " pixels has " + raster.getNumSampleDimensions() + " bands and extent of " + raster.getEnvelope().toString() + System.lineSeparator());
        for (int i = 0; i < raster.getNumSampleDimensions(); i++) {
            builder.append("band " + i + " of pixtype " + raster.getSampleDimension(i).getColorModel().getTransferType() + " is in-db with NODATA value of " + raster.getSampleDimension(i).getNoDataValues()[0] + System.lineSeparator());
        }
        return NodeValue.makeString(builder.toString());
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{};
    }

}
