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
package de.hsmainz.cs.semgis.arqextension.linestring;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotools.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.FactoryException;

public class Segmentize extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry track = geometry.getXYGeometry();
            double segmentLength = arg1.getDouble();
            CoordinateReferenceSystem crs = geometry.getCRS();

            List<Geometry> geometries = createSegments(track, segmentLength, crs);
            List<String> results = new ArrayList<>(geometries.size());
            for (Geometry geom : geometries) {
                GeometryWrapper geomWrapper = GeometryWrapper.createGeometry(geom, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
                String result = geomWrapper.asLiteral().toString();
                results.add(result);
            }

            //Returning the list of space delimited literals. This is the same as GROUP_CONCAT.
            //Correct splitting of results for use in query would need a Property Function.
            return NodeValue.makeNodeString(String.join(" ", results));
        } catch (DatatypeFormatException | FactoryException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

    public List<Geometry> createSegments(Geometry track, double segmentLength, CoordinateReferenceSystem crs) throws NoSuchAuthorityCodeException, FactoryException {

        GeodeticCalculator calculator = new GeodeticCalculator(crs); //TODO shouldn't this check whether the SRS/CRS is Geographic? Can be found in GeometryWrapper.getSRSInfo().
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING));

        List<Coordinate> coordinates = new ArrayList<>(track.getCoordinates().length);
        Collections.addAll(coordinates, track.getCoordinates());

        double accumulatedLength = 0;
        List<Coordinate> lastSegment = new ArrayList<>();
        List<Geometry> segments = new ArrayList<>();
        Iterator<Coordinate> itCoordinates = coordinates.iterator();

        for (int i = 0; itCoordinates.hasNext() && i < coordinates.size() - 1; i++) {
            Coordinate c1 = coordinates.get(i);
            Coordinate c2 = coordinates.get(i + 1);

            lastSegment.add(c1);

            calculator.setStartingGeographicPoint(c1.x, c1.y);
            calculator.setDestinationGeographicPoint(c2.x, c2.y);

            double length = calculator.getOrthodromicDistance();

            if (length + accumulatedLength >= segmentLength) {
                double offsetLength = segmentLength - accumulatedLength;
                double ratio = offsetLength / length;
                double dx = c2.x - c1.x;
                double dy = c2.y - c1.y;

                Coordinate segmentationPoint = new Coordinate(c1.x + (dx * ratio),
                        c1.y + (dy * ratio));

                lastSegment.add(segmentationPoint); // Last point of the segment is the segmentation point
                segments.add(geometryFactory.createLineString(lastSegment.toArray(new Coordinate[lastSegment.size()])));

                lastSegment = new ArrayList<>(); // Resets the variable since a new segment will be built
                accumulatedLength = 0D;
                coordinates.add(i + 1, segmentationPoint);
            } else {
                accumulatedLength += length;
            }
        }

        lastSegment.add(coordinates.get(coordinates.size() - 1)); // Because the last one is never added in the loop above
        segments.add(geometryFactory.createLineString(lastSegment.toArray(new Coordinate[lastSegment.size()])));

        return segments;
    }

}
