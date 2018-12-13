<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<aside class="sidebar">
                    <div class="sidebar-container">
                        <a href="current_schedule" class="no-style">
                            <div class="sidebar-header">
                            <div class="brand2">
                
                                A2A Bus System
                            </div>
                        </div>
                    </a>
                        <nav class="menu">
                            <ul class="sidebar-menu metismenu" id="sidebar-menu">
                                <li id="scheduleMng" class="re">
                                    <a href="current_schedule">
                                        <i class="fa fa-calendar"></i> Schedules </a>
                                </li>
                                <li id="bookingMng" class="re">
                                    <a href="admin_booking">
                                        <i class="fa fa-list"></i> Bookings </a>
                                </li>

                                <%--<li id="bookingRequestMng" class="re">--%>
                                    <%--<a href="admin_booking_request">--%>
                                        <%--<i class="fa fa-envelope-open"></i> Booking Requests <span style="background-color: red;" class="badge pull-right" id="notii"></span></a>--%>
                                        <%----%>
                                <%--</li>--%>



                                <li id="busMng" class="re">
                                    <a href="bus_management">
                                        <i class="fa fa-bus"></i> Manage Buses </a>
                                </li>



                                <li id="locationMng" class="re">
                                    <a href="location_management">
                                        <i class="fa fa-institution"></i> Manage Locations </a>
                                </li>



                                <li id="reportMng" class="re">
                                    <a href="#">
                                        <i class="fa fa-bar-chart"></i> Reports <span id="ddr1" style="margin-top:3px;margin-right: 3px;" class="pull-right"><i class="fa fa-angle-right "></i></span>    <span id="ddr2" style="margin-top:3px;margin-right: 3px;" class="irr pull-right"><i class="fa fa-angle-down "></i></span></a>
                                </li>

                                <li id="breport" class="irr ir2">
                                    <a href="report">
                                         <span style="margin-left: 30px;">Bookings Report </span></a>
                                </li>
                                <li id="sreport" class="irr ir2">
                                    <a href="sReport">
                                        <span style="margin-left: 30px;">Schedules Report</span> </a>
                                </li>

                                <li id="settingMng">
                                    <a href="#">
                                        <i class="fa fa-wrench"></i> Setting<span id="dds1" style="margin-top:3px;margin-right: 3px;" class="pull-right"><i class="fa fa-angle-right "></i></span>    <span id="dds2" style="margin-top:3px;margin-right: 3px;" class="irr pull-right"><i class="fa fa-angle-down "></i></span> </a>
                                </li>

                                <li id="userMng" class="irr ir">
                                    <a href="create_user">
                                        <span style="margin-left: 30px;"> Create User</span> </a>
                                </li>
                                <%--<li id="depttimeMng" class="irr ir">--%>
                                    <%--<a href="departure_time">--%>
                                        <%--<span style="margin-left: 30px;"> Manage Departure Time</span> </a>--%>
                                <%--</li>--%>
                                 <%--<li id="deptdateMng" class="irr ir">--%>
                                    <%--<a href="departure_date">--%>
                                        <%--<span style="margin-left: 30px;"> Manage Departure Date</span> </a>--%>
                                <%--</li>--%>
                                <%--<li id="priceMng" class="irr ir">--%>
                                    <%--<a href="pricemng">--%>
                                        <%--<span style="margin-left: 30px;"> Manage Prices</span> </a>--%>
                                <%--</li>--%>
                                <li id="ticketMng" class="irr ir">
                                    <a href="ticketmng">
                                        <span style="margin-left: 30px;"> Manage Tickets</span> </a>
                                </li>
                                

                                    
                                
                               
                                
                            </ul>
                        </nav>
                    </div>
                    <footer class="sidebar-footer">
                        <ul class="sidebar-menu metismenu" id="customize-menu">
                            <li>
                                <ul>
                                    <li class="customize">
                                        
                                        <div class="customize-item">
                                            
                                        </div>
                                    </li>
                                </ul>
                                Powered by: KIT
                            </li>
                        </ul>
                    </footer>
                    
                </aside>
                <div class="sidebar-overlay" id="sidebar-overlay"></div>
                <div class="sidebar-mobile-menu-handle" id="sidebar-mobile-menu-handle"></div>
                <div class="mobile-menu-handle"></div>
                <script>
                    
                 </script>