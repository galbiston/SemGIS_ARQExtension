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
import org.apache.sis.geometry.Envelope2D;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.CoordinateXY;
import org.opengis.referencing.operation.TransformException;

public class PixelAsPoint extends RasterSpatialFunction {

    @Override
    protected String[] getRestOfArgumentTypes() {
        // TODO Auto-generated method stub
        return new String[]{XSD.xint.getURI(), XSD.xint.getURI()};
    }

    @Override
    protected NodeValue exec(GridCoverage2D raster, GeometryWrapper geometryWrapper, Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env) {
        Integer x = evalArgs.get(0).getInteger().intValue();
        Integer y = evalArgs.get(0).getInteger().intValue();

        Envelope2D pixelEnvelop;
        try {
            pixelEnvelop = raster.getGridGeometry().getGridToCRS2D().transform(new GridEnvelope2D(x, y, 1, 1),null);

            CoordinateXY coord = new CoordinateXY(pixelEnvelop.getCenterX(), pixelEnvelop.getCenterY());
            GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(coord, geometryWrapper.getSrsURI(), geometryWrapper.getGeometryDatatypeURI());
            return pointWrapper.asNodeValue();
        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
    }

}
