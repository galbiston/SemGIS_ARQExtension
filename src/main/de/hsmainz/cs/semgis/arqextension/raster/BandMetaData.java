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
import org.apache.jena.vocabulary.XSD;
import org.geotoolkit.coverage.grid.GridCoverage2D;

public class BandMetaData extends RasterSpatialFunction {

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding,
            List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        Integer bandNum;
        if (evalArgs.isEmpty()) {
            bandNum = 1;
        }
        bandNum = evalArgs.get(0).getInteger().intValue();
        if (bandNum > raster.getNumSampleDimensions()) {
            return NodeValue.nvNothing;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("rid \t pixeltype \t nodatavalue \t isoutdb \t path" + System.lineSeparator());
        builder.append(bandNum + "\t" + raster.getSampleDimension(bandNum).getColorModel().getTransferType()
                + "\t" + raster.getSampleDimension(bandNum).getNoDataValues() + "\t\t\t" + System.lineSeparator());
        return NodeValue.makeString(builder.toString());
    }

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{XSD.xint.getURI()};
    }

}
