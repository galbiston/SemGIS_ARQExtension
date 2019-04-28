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
package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.List;
import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.locationtech.jts.geom.util.AffineTransformation;

public class Affine extends FunctionBase {

    @Override
    public NodeValue exec(List<NodeValue> args) {
        AffineTransformation transform = new AffineTransformation();

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if (args.size() != 7 || args.size() != 13) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes seven or thirteen arguments");
        }
    }

}
