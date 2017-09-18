/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

/**
 *
 * @author Juneau
 */
@WebServlet("/SSETestServlet")
public class SSETestServlet extends HttpServlet {

    private static final String SSE_SVC = "http://localhost:8080/AcmePools/rest/sse/subscribe";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebTarget target = ClientBuilder.newClient().target(SSE_SVC);
        try (
                final SseEventSource eventSource
                = SseEventSource.target(target)
                        .reconnectingEvery(5, TimeUnit.SECONDS)
                        .build()) {
                    eventSource.register(System.out::println); // InboundSseEvent consumer
                    eventSource.open();
                    for(int counter = 0; counter < 3; counter ++){
                    target.request().post(Entity.text("message: " + counter));
                            }
                } catch (Exception e){
                    System.out.println("exception: " + e);
                }
               
                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
                out.println("<html>");
                out.println("<body>"
                        + "Check server log...");

                out.println("</body>");
                out.println("</html>");
    }
}

