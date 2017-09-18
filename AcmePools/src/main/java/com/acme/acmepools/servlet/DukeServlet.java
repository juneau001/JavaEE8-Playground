/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;

/**
 *
 * @author Juneau
 */
@WebServlet(value = {"/DukeServlet"})
public class DukeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        PushBuilder pushBuilder = req.newPushBuilder();
        if (pushBuilder != null) {
            pushBuilder
                    .path("resources/images/dukewaving.gif")
                    .addHeader("content-type", "image/gif")
                    .push();
            // Push more than one resource
//            pushBuilder
//                    .path("resources/images/dukewaving.gif")
//                    .addHeader("content-type", "image/gif")
//                    .push();
        }
        try (PrintWriter respWriter = resp.getWriter();) {
            respWriter.write("<html>"
                    + "<img src='resources/images/dukewaving.gif'>"
                    + "</html>");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
