//For populating the sidebar
//Also for access control


// =========== ESSENTIAL ========== //

var begin = '';
begin += '\
			<div class="page-sidebar navbar-collapse collapse">\
                <ul class="page-sidebar-menu  page-sidebar-menu-light page-sidebar-menu-hover-submenu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">\
                    <li class="sidebar-toggler-wrapper hide">\
                        <div class="sidebar-toggler">\
                            <span></span>\
                        </div>\
                    </li>\
                    ';

var end = '';
end += '\
		        <p></p>\
		</div>\
		';



// =========== HEADERS ========== //

var myWorkspaceHeader = '';
myWorkspaceHeader += '\
				        <li class="heading">\
				            <h3 style="color:yellow" class="uppercase">My Workspace</h3>\
				        </li>\
				       	';

var commsHeader = '';
commsHeader += '\
				<li class="heading">\
                    <h3 style="color:yellow" class="uppercase">Communications</h3>\
                </li>\
                ';

var ehrHeader = '';
ehrHeader += '\
			<li class="heading">\
	            <h3 style="color:yellow" class="uppercase">E-HR</h3>\
            </li>\
	        ';

var corpWorkspaceHeader = '';
corpWorkspaceHeader += '\
						<li class="heading">\
                            <h3 style="color:yellow" class="uppercase">My Corporate Workspace</h3>\
                        </li>\
                        ';



// =========== SIDEBAR MENUS ITEMS ========== //

var roster = '';
roster += '\
	        <!--My Roster-->\
	        <li class="nav-item ">\
	            <a href=underconstruction.html class="nav-link " id="rosterMenu">\
	                <i class="fa fa-building-o"></i>\
	                <span class="title">My Roster</span>\
	            </a>\
	        </li>\
	        ';

var trainMgmt = '';
trainMgmt += '\
				<li class="nav-item ">\
                    <a href="javascript:;" class="nav-link nav-toggle" id="trainsMgmtMenu">\
                        <i class="fa fa-subway"></i>\
                        <span class="title">Trains Management</span>\
                        <span class="arrow"></span>\
                    </a>\
                    <ul class="sub-menu">\
                        <li class="nav-item start " id="timetableSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-calendar"></i>\
                                <span class="title">View Timetable</span>\
                            </a>\
                        </li>\
                        <li class="nav-item start " id="trainRouteAssignSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-map-o"></i>\
                                <span class="title">Train-Route Assignment</span>\
                            </a>\
                        </li>\
                        <li class="nav-item start " id="trainConfigSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-gears"></i>\
                                <span class="title">Train Configuration</span>\
                            </a>\
                        </li>\
                        <li class="nav-item start " id="liveStatusSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-flash"></i>\
                                <span class="title">Live Status</span>\
                            </a>\
                        </li>\
                    </ul>\
                </li>\
                ';

var inventAsset = '';
inventAsset += '\
                <li class="nav-item  ">\
                    <a href="javascript:;" class="nav-link nav-toggle" id="inventAssetInfraMenu">\
                        <i class="fa fa-cubes"></i>\
                        <span class="title">Inventory, Asset and Infrastructure</span>\
                        <span class="arrow"></span>\
                    </a>\
                    <ul class="sub-menu">\
                        <li class="nav-item  ">\
                            <a href="inventory-viewall.html" class="nav-link nav-toggle" id="inventoryMgmtSubmenu">\
                                <i class="fa fa-cube"></i>\
                                <span class="title">Inventory Management</span>\
                            </a>\
                        </li>\
                        <li class="nav-item " id="assetSubmenu">\
                            <a href="asset-viewall.html" class="nav-link ">\
                                <i class="fa fa-tags"></i>\
                                <span class="title">Asset Management</span>\
                            </a>\
                        </li>\
                        <li class="nav-item " id="dispatchItemsSubmenu">\
                            <a href="item-request-viewall.html" class="nav-link ">\
                                <i class="fa fa-cart-plus"></i>\
                                <span class="title">Item Requests</span>\
                            </a>\
                        </li>\
                        <li class="nav-item " id="dispatchItemsSubmenu">\
                            <a href="transfer-request-viewall.html" class="nav-link ">\
                                <i class="fa fa-truck"></i>\
                                <span class="title">Transfer Requests</span>\
                            </a>\
                        </li>\
                        <li class="nav-item " id="procurementSubmenu">\
                            <a href="rfp-viewall.html" class="nav-link ">\
                                <i class="fa fa-shopping-cart"></i>\
                                <span class="title">Procurement Requests</span>\
                            </a>\
                        </li>\
                    </ul>\
                </li>\
                ';


var infraMgmt = '';
infraMgmt += '\
				<li class="nav-item  ">\
                    <a href="infra-viewall.html" class="nav-link nav-toggle" id="infraMgmtMenu">\
                        <i class="fa fa-cubes"></i>\
                        <span class="title">Infrastructure Management</span>\
                    </a>\
                </li>\
                ';


var procure = '';
procure += '\
                <li class="nav-item " id="procurementSubmenu">\
                    <a href="procure-viewall.html" class="nav-link ">\
                        <i class="fa fa-credit-card"></i>\
                        <span class="title">Procurement Tasks</span>\
                    </a>\
                </li>\
                ';


var maintain = '';
maintain += '\
			<li class="nav-item ">\
                <a href="javascript:;" class="nav-link nav-toggle" id="maintainMgmtMenu">\
                    <i class="fa fa-wrench"></i>\
                    <span class="title">Maintenance</span>\
                    <span class="arrow"></span>\
                </a>\
                <ul class="sub-menu">\
                    <li class="nav-item  ">\
                        <a href="javascript:;" class="nav-link nav-toggle" id="maintainRequestSubmenu">\
                            <i class="fa fa-sticky-note-o"></i>\
                            <span class="title">Maintenance Request</span>\
                            <span class="arrow"></span>\
                        </a>\
                            <ul class="sub-menu">\
                                <li class="nav-item " id="newMaintainRequestSubsubmenu">\
                                    <a href=# class="nav-link "><i class="fa fa-plus-square"></i>\
                                        <span class="title">New Request</a></span>\
                                </li>\
                                <li class="nav-item " id="viewAllMaintainRequestsSubsubmenu">\
                                    <a href=# class="nav-link "><i class="fa fa-eye"></i>\
                                        <span class="title"> View All Requests</a></span>\
                                </li>\
                            </ul>\
                    </li>\
                    <li class="nav-item " id="schedulingSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-calendar-plus-o"></i>\
                            <span class="title">Scheduling</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  ">\
                        <a href="javascript:;" class="nav-link nav-toggle" id="reportingSubmenu">\
                            <i class="fa fa-file-text-o"></i>\
                            <span class="title">Reporting</span>\
                            <span class="arrow"></span>\
                        </a>\
                            <ul class="sub-menu">\
                                <li class="nav-item  ">\
                                    <a href="javascript:;" class="nav-link nav-toggle" id="inspectionReportSubsubmenu">\
                                        <i class="fa fa-gear"></i>\
                                        <span class="title">Inspection Report</span>\
                                        <span class="arrow"></span>\
                                    </a>\
                                <ul class="sub-menu">\
                                    <li class="nav-item " id="newInspectionReportSubsubsubmenu">\
                                        <a href=# class="nav-link "><i class="fa fa-plus-square"></i>\
                                            <span class="title">New Inspection Report</a></span>\
                                    </li>\
                                    <li class="nav-item " id="viewAllInspectionReportSubsubsubmenu">\
                                        <a href=# class="nav-link "><i class="fa fa-eye"></i>\
                                            <span class="title">View All Inspection Report</a></span>\
                                    </li>\
                                </ul>\
                                <li class="nav-item  ">\
                                    <a href="javascript:;" class="nav-link nav-toggle" id="maintenanceReportSubsubmenu">\
                                        <i class="fa fa-gavel"></i>\
                                        <span class="title">Maintenance Report</span>\
                                        <span class="arrow"></span>\
                                    </a>\
                                <ul class="sub-menu">\
                                    <li class="nav-item " id="newMaintenanceReportSubsubsubmenu">\
                                        <a href=# class="nav-link "><i class="fa fa-plus-square"></i>\
                                            <span class="title">New Maintenance Report</a></span>\
                                    </li>\
                                    <li class="nav-item " id="viewAllMaintenanceReportSubsubsubmenu">\
                                        <a href=# class="nav-link "><i class="fa fa-eye"></i>\
                                            <span class="title">View All Maintenance Report</a>\
                                    </li>\
                                </ul>\
                            </ul>\
                        </li>\
                    <li class="nav-item " id="taskAssignSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-server"></i>\
                            <span class="title">Maintenance Task Assignment</span>\
                        </a>\
                    </li>\
                </ul>\
            </li>\
            ';

var operations = '';
operations += '\
				<li class="nav-item  ">\
			        <a href="javascript:;" class="nav-link nav-toggle" id="opsMgmtMenu">\
			            <i class="icon-settings"></i>\
			            <span class="title">Operations</span>\
			            <span class="arrow"></span>\
			        </a>\
			        <ul class="sub-menu">\
			            <li class="nav-item " id="trainLiveStatusSubmenu">\
			                <a href=# class="nav-link ">\
			                    <i class="fa fa-flash"></i>\
			                    <span class="title">Train LiveStatus</span>\
			                </a>\
			            </li>\
			            <li class="nav-item " id="ticketingSubmenu">\
			                <a href=# class="nav-link ">\
			                    <i class="fa fa-ticket"></i>\
			                    <span class="title">Ticketing Management</span>\
			                </a>\
			            </li>\
			            <li class="nav-item " id="driverReportSubmenu">\
			                <a href=# class="nav-link ">\
			                    <i class="fa fa-file-text-o"></i>\
			                    <span class="title">Post-trip Driver Report</span>\
			                </a>\
			            </li>\
			            <li class="nav-item " id="stationReportSubmenu">\
			                <a href=# class="nav-link ">\
			                    <i class="fa fa-file-text-o"></i>\
			                    <span class="title">Post-shift Station Report</span>\
			                </a>\
			            </li>\
			            <li class="nav-item " id="cashCollectionReportSubmenu">\
			                <a href=# class="nav-link ">\
			                    <i class="fa fa-money"></i>\
			                    <span class="title">Cash Collection Accountability Report</span>\
			                </a>\
			            </li>\
			        </ul>\
			    </li>\
			    ';

var announce = '';
announce += '\
			<li class="nav-item  ">\
                <a href=# class="nav-link " id="announcementsMenu">\
                    <i class="fa fa-bullhorn"></i>\
                    <span class="title">Announcements</span>\
                </a>\
            </li>\
            ';


var message = '';
message += '\
			<li class="nav-item  ">\
                <a href="javascript:;" class="nav-link nav-toggle" id="messagingMenu">\
                    <i class="fa fa-envelope-o"></i>\
                    <span class="title">Messaging</span>\
                    <span class="arrow"></span>\
                </a>\
                <ul class="sub-menu">\
                    <li class="nav-item " id="inboxSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-inbox"></i>\
                            <span class="title">Inbox</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="sentMsgesSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-envelope-square"></i>\
                            <span class="title">Sent Messages</span>\
                        </a>\
                    </li>\
                </ul>\
            </li>\
            ';    

var liveUpdates = '';
liveUpdates += '\
				<li class="nav-item  ">\
                    <a href="javascript:;" class="nav-link nav-toggle" id="liveUpdatesMenu">\
                        <i class="fa fa-flash"></i>\
                        <span class="title">Live Updates</span>\
                        <span class="arrow"></span>\
                    </a>\
                    <ul class="sub-menu">\
                        <li class="nav-item  " id="massCommSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-users"></i>\
                                <span class="title">Mass Communication</span>\
                            </a>\
                        </li>\
                        <li class="nav-item  " id="passengerUpdateSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-user-secret"></i>\
                                <span class="title">Passenger LiveStatus Update</span>\
                            </a>\
                        </li>\
                    </ul>\
                </li>\
                ';


var custComms = '';
custComms += '\
			<li class="nav-item  ">\
                <a href="javascript:;" class="nav-link nav-toggle" id="custCommsMenu">\
                    <i class="fa fa-microphone"></i>\
                    <span class="title">Customers Communications</span>\
                    <span class="arrow"></span>\
                </a>\
                <ul class="sub-menu">\
                    <li class="nav-item " id="custFeedbacksSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-smile-o"></i>\
                            <span class="title">Customers Feedbacks</span>\
                        </a>\
                    </li>\
                </ul>\
            </li>\
            ';

var hrhrhr = '';
hrhrhr += '\
			<li class="nav-item  ">\
                <a href="javascript:;" class="nav-link nav-toggle" id="hrMgmtMenu">\
                    <i class="fa fa-user-secret"></i>\
                    <span class="title">HR Management</span>\
                    <span class="arrow"></span>\
                </a>\
                <ul class="sub-menu">\
                    <li class="nav-item  " id="rosterPlanningSubmenu"">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-table"></i>\
                            <span class="title">Roster Planning</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="staffSubmenu"">\
                        <a href="staff-viewdetails.html" class="nav-link ">\
                            <i class="fa fa-database"></i>\
                            <span class="title">Staff Profile</span>\
                        </a>\
                    </li>\
                    <li class="nav-item ">\
                        <a href="javascript:;" class="nav-link nav-toggle" id="employeeMgmtSubmenu">\
                            <i class="fa fa-briefcase"></i>\
                            <span class="title">Employee Management</span>\
                            <span class="arrow"></span>\
                        </a>\
                        <ul class="sub-menu">\
                            <li class="nav-item " id="employeePerformanceMgmtSubsubmenu">\
                                <a href=# class="nav-link ">\
                                    <i class="fa fa-line-chart"></i>\
                                    <span class="title">Employee Performance Management</a></span>\
                            </li>\
                            <li class="nav-item " id="employeeDevelopmentMgmtSubsubmenu">\
                                <a href=# class="nav-link ">\
                                    <i class="fa fa-book"></i>\
                                    <span class="title">Employee Development Management</a></span>\
                            </li>\
                            <li class="nav-item " id="employeeLeaveMgmtSubsubmenu">\
                                <a href="underconstruction.html" class="nav-link ">\
                                    <i class="fa fa-leaf"></i>\
                                    <span class="title">Employee Leave Management</a></span>\
                            </li>\
                        </ul>\
                    </li>\
                </ul>\
            </li>\
            ';

var perform = '';
perform += '\
			<li class="nav-item ">\
                <a href="javascript:;" class="nav-link nav-toggle" id="performanceMenu">\
                    <i class="fa fa-area-chart"></i>\
                    <span class="title">My Performance</span>\
                    <span class="arrow"></span>\
                </a>\
                <ul class="sub-menu">\
                    <li class="nav-item " id="attendanceSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-calendar-check-o"></i>\
                            <span class="title">My Attendance</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="appraisalsSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-check-square"></i>\
                            <span class="title">My Appraisals</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="violationsSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-frown-o"></i>\
                            <span class="title">My Violations Records</span>\
                        </a>\
                    </li>\
                </ul>\
            </li>\
            ';

var develop = '';
develop += '\
			<li class="nav-item ">\
	            <a href="javascript:;" class="nav-link nav-toggle" id="developmentMenu">\
	                <i class="icon-layers"></i>\
	                <span class="title">My Development</span>\
	                <span class="arrow"></span>\
	            </a>\
	            <ul class="sub-menu">\
	                <li class="nav-item " id="newTrainingSubmenu">\
	                    <a href=# class="nav-link ">\
	                        <i class="fa fa-book"></i>\
	                        <span class="title">Available Training & Classes</span>\
	                    </a>\
	                </li>\
	            </ul>\
	        </li>\
	        ';

var leave = '';
leave += '\
			<li class="nav-item ">\
	            <a href=# class="nav-link " id="leaveMenu">\
	                <i class="fa fa-leaf"></i>\
	                <span class="title">Leave Management</span>\
	            </a>\
	        </li>\
	        ';

var compPeform = '';
compPeform += '\
				<li class="nav-item ">\
	                <a href=# class="nav-link " id="companyPerfMenu">\
	                    <i class="fa fa-pie-chart"></i>\
	                    <span class="title">Company Performance</span>\
	                </a>\
	            </li>\
	            ';

var starfish = '';
starfish += '\
			<li class="nav-item ">\
                <a href="javascript:;" class="nav-link nav-toggle" id="starFishMgmtMenu">\
                    <i class="fa fa-star-o"></i>\
                    <span class="title">StarFish</span>\
                    <span class="arrow"></span>\
                </a>\
                <ul class="sub-menu">\
                    <li class="nav-item " id="pointsMgmtSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-rub"></i>\
                            <span class="title">Points Management</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="merchantMgmtSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-briefcase"></i>\
                            <span class="title">Merchants Management</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="commuterAcctMgmtSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-database"></i>\
                            <span class="title">Commuters Accounts Management</span>\
                        </a>\
                    </li>\
                </ul>\
            </li>\
            ';

var extCompMgmt = '';
extCompMgmt += '\
				<li class="nav-item ">\
                    <a href="javascript:;" class="nav-link nav-toggle" id="externalCompMgmtMenu">\
                        <i class="fa fa-industry"></i>\
                        <span class="title">External Companies Management</span>\
                        <span class="arrow"></span>\
                    </a>\
                    <ul class="sub-menu">\
                        <li class="nav-item " id="externalCompAcctMgmtSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-database"></i>\
                                <span class="title">External Company Accounts Management</span>\
                            </a>\
                        </li>\
                        <li class="nav-item  " id="tenderMgmtSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-clone"></i>\
                                <span class="title">Tenders Management</span>\
                            </a>\
                        </li>\
                        <li class="nav-item  " id="leaseMgmtSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-pencil-square-o"></i>\
                                <span class="title">Lease Management</span>\
                            </a>\
                        </li>\
                        <li class="nav-item  " id="adsMgmtSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-picture-o"></i>\
                                <span class="title">Ads Management</span>\
                            </a>\
                        </li>\
                    </ul>\
                </li>\
                ';

var costCentre = '';
costCentre += '\
				<li class="nav-item ">\
                    <a href=# class="nav-link nav-toggle" id="costCentreMgmtMenu">\
                        <i class="fa fa-dollar"></i>\
                        <span class="title">Cost Centre Management</span>\
                    </a>\
                </li>\
                ';

var farebox = '';
farebox += '\
			<li class="nav-item ">\
                <a href=# class="nav-link nav-toggle" id="fareboxMgmtMenu">\
                    <i class="fa fa-archive"></i>\
                    <span class="title">Farebox Management</span>\
                </a>\
                <ul class="sub-menu">\
                    <li class="nav-item " id="fareboxAdjustSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-ticket"></i>\
                            <span class="title">Farebox Adjustment</span>\
                        </a>\
                    </li>\
                </ul>\
            </li>\
            ';

var realEstate = '';
realEstate += '\
				<li class="nav-item ">\
                    <a href="javascript:;" class="nav-link nav-toggle" id="realEstateMgmtMenu">\
                        <i class="fa fa-home"></i>\
                        <span class="title">Real Estate Management</span>\
                        <span class="arrow"></span>\
                    </a>\
                    <ul class="sub-menu">\
                        <li class="nav-item " id="leaseAreasSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-pencil-square-o"></i>\
                                <span class="title">Leaseable Areas Management</span>\
                            </a>\
                        </li>\
                        <li class="nav-item  " id="adsSpacesSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-picture-o"></i>\
                                <span class="title">Ads Spaces Management</span>\
                            </a>\
                        </li>\
                    </ul>\
                </li>\
                ';

var trainNetwork = '';
trainNetwork += '\
				<li class="nav-item ">\
                    <a href="javascript:;" class="nav-link nav-toggle" id="trainNetworkMgmtMenu">\
                        <i class="fa fa-map-signs"></i>\
                        <span class="title">Train Network Management</span>\
                        <span class="arrow"></span>\
                    </a>\
                    <ul class="sub-menu">\
                        <li class="nav-item " id="networkDesignSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-code-fork"></i>\
                                <span class="title">Network Design</span>\
                            </a>\
                        </li>\
                        <li class="nav-item  " id="timetableDesignSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-table"></i>\
                                <span class="title">Timetable Design</span>\
                            </a>\
                        </li>\
                        <li class="nav-item  " id="routeDesignSubmenu">\
                            <a href=# class="nav-link ">\
                                <i class="fa fa-road"></i>\
                                <span class="title">Route Design</span>\
                            </a>\
                        </li>\
                    </ul>\
                </li>\
                ';

var audit = '';
audit += '\
			<li class="nav-item ">\
                <a href="javascript:;" class="nav-link nav-toggle" id="auditMenu">\
                    <i class="fa fa-gavel"></i>\
                    <span class="title">Audit</span>\
                    <span class="arrow"></span>\
                </a>\
                <ul class="sub-menu">\
                    <li class="nav-item " id="auditInspectionReportsSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-gear"></i>\
                            <span class="title">Audit Inspection Reports</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="auditMaintenanceReportsSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-wrench"></i>\
                            <span class="title">Audit Maintenance Reports</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="auditPosttripsReportsSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-subway"></i>\
                            <span class="title">Audit Post-trips Reports</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="auditPostshiftsReportsSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-building-o"></i>\
                            <span class="title">Audit Post-shifts Reports</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="auditDispatchesSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-truck"></i>\
                            <span class="title">Audit Dispatches</span>\
                        </a>\
                    </li>\
                    <li class="nav-item  " id="auditProcurementSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-shopping-cart"></i>\
                            <span class="title">Audit Procurement</span>\
                        </a>\
                    </li>\
                </ul>\
            </li>\
            ';

var accessControl = '';
accessControl += '\
					<li class="nav-item ">\
	                    <a href="javascript:;" class="nav-link nav-toggle" id="acmMgmtMenu">\
	                        <i class="fa fa-tasks"></i>\
	                        <span class="title">Access Control Management</span>\
	                        <span class="arrow"></span>\
	                    </a>\
	                    <ul class="sub-menu">\
	                        <li class="nav-item " id="intAcctsACMSubmenu">\
	                            <a href=# class="nav-link ">\
	                                <i class="fa fa-sign-in"></i>\
	                                <span class="title">Internal Accounts ACM</span>\
	                            </a>\
	                        </li>\
	                        <li class="nav-item " id="extAcctsACMSubmenu">\
	                            <a href=# class="nav-link ">\
	                                <i class="fa fa-sign-out"></i>\
	                                <span class="title">External Accounts ACM</span>\
	                            </a>\
	                        </li>\
	                    </ul>\
	                </li>\
	                ';

var acctMgmt = '';
acctMgmt += '\
			<li class="nav-item ">\
                <a href="javascript:;" class="nav-link nav-toggle" id="acctsMgmtMenu">\
                    <i class="fa fa-database"></i>\
                    <span class="title">Accounts Management</span>\
                    <span class="arrow"></span>\
                </a>\
                <ul class="sub-menu">\
                    <li class="nav-item " id="intAcctsMgmtSubmenu">\
                        <a href="acct-int-viewall.html" class="nav-link ">\
                            <i class="fa fa-sign-in"></i>\
                            <span class="title">Internal Accounts</span>\
                        </a>\
                    </li>\
                    <li class="nav-item " id="extAcctsMgmtSubmenu">\
                        <a href=# class="nav-link ">\
                            <i class="fa fa-sign-out"></i>\
                            <span class="title">External Accounts</span>\
                        </a>\
                    </li>\
                </ul>\
            </li>\
            ';

var webContent = '';
webContent += '\
				<li class="nav-item ">\
	                <a href="javascript:;" class="nav-link nav-toggle" id="webContentsMgmtMenu">\
	                    <i class="fa fa-connectdevelop"></i>\
	                    <span class="title">Web Contents Management</span>\
	                    <span class="arrow"></span>\
	                </a>\
	                <ul class="sub-menu">\
	                    <li class="nav-item " id="pagesSubmenu">\
	                        <a href=# class="nav-link ">\
	                            <i class="fa fa-clone"></i>\
	                            <span class="title">Pages</span>\
	                        </a>\
	                    </li>\
	                </ul>\
	            </li>\
	            ';



var result = '';
result = begin + myWorkspaceHeader + roster + trainMgmt + inventAsset + maintain
		 + operations + commsHeader + announce + message + liveUpdates + custComms  
		 + ehrHeader + hrhrhr + perform + develop + leave + corpWorkspaceHeader + compPeform 
		 + starfish + extCompMgmt + costCentre + farebox + realEstate + trainNetwork 
		 + audit + accessControl + acctMgmt + webContent + end;


var start = '';
start = begin + ehrHeader + perform + develop + leave;

document.getElementById('beginning').innerHTML = start;
document.getElementById('IA').innerHTML = inventAsset;
document.getElementById('IM').innerHTML = infraMgmt;
document.getElementById('PO').innerHTML = procure;
document.getElementById('end').innerHTML = end;

//document.getElementById('sidebar').innerHTML = result; //display the generated sidebar