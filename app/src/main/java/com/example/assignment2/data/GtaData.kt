package com.example.assignment2.data

object GtaData {
    val locations = listOf(
        // Oshawa (1-10)
        Triple("Oshawa Downtown (City Hall)", 43.8975, -78.8658),
        Triple("Oshawa Centre Shopping Mall", 43.8863, -78.8856),
        Triple("Ontario Tech University Main Campus", 43.9454, -78.8968),
        Triple("Durham College Oshawa Campus", 43.9439, -78.8942),
        Triple("Tribute Communities Centre", 43.8950, -78.8590),
        Triple("Lakeview Park Oshawa", 43.8643, -78.8252),
        Triple("Oshawa GO Station", 43.8710, -78.8850),
        Triple("Parkwood Estate Oshawa", 43.9031, -78.8667),
        Triple("Canadian Automotive Museum", 43.8967, -78.8606),
        Triple("Lakeridge Health Oshawa", 43.9065, -78.8672),

        // Ajax (11-20)
        Triple("Ajax Town Centre", 43.8509, -79.0204),
        Triple("Ajax Community Centre", 43.8402, -79.0345),
        Triple("Rotary Park Ajax", 43.8315, -79.0221),
        Triple("Ajax GO Station", 43.8601, -79.0245),
        Triple("McLean Community Centre Ajax", 43.8722, -79.0063),
        Triple("Ajax Pickering Hospital", 43.8481, -79.0286),
        Triple("Paradise Beach Ajax", 43.8290, -79.0055),
        Triple("Greenwood Conservation Area Ajax", 43.9214, -79.0628),
        Triple("Deer Creek Golf Club Ajax", 43.9110, -79.0298),
        Triple("Casino Ajax", 43.8465, -79.0094),

        // Pickering (21-30)
        Triple("Pickering Town Centre", 43.8384, -79.0868),
        Triple("Pickering GO Station", 43.8322, -79.0860),
        Triple("Pickering Millennium Waterfront Trail", 43.8115, -79.0890),
        Triple("Chestnut Hill Developments Recreation Complex", 43.8370, -79.0895),
        Triple("Petticoat Creek Conservation Area", 43.8055, -79.1062),
        Triple("Pickering Museum Village", 43.9062, -79.0765),
        Triple("Frenchman's Bay Marina", 43.8152, -79.0965),
        Triple("Seaton Trail Pickering", 43.8621, -79.1134),
        Triple("Altona Forest Pickering", 43.8360, -79.1350),
        Triple("Pickering Heritage & Community Centre", 43.8395, -79.0910),

        // Scarborough (31-40)
        Triple("Scarborough Town Centre", 43.7764, -79.2580),
        Triple("Scarborough Bluffs Park", 43.7088, -79.2319),
        Triple("University of Toronto Scarborough (UTSC)", 43.7844, -79.2287),
        Triple("Toronto Zoo Scarborough", 43.8197, -79.1834),
        Triple("Rouge National Urban Park", 43.8225, -79.1550),
        Triple("Centennial College Progress Campus", 43.7850, -79.2272),
        Triple("Guild Park and Gardens Scarborough", 43.7483, -79.1925),
        Triple("Scarborough Health Network General Hospital", 43.7682, -79.2425),
        Triple("Thomson Memorial Park Scarborough", 43.7570, -79.2550),
        Triple("Kennedy Station Scarborough", 43.7325, -79.2638),

        // Downtown Toronto (41-55)
        Triple("Downtown Toronto (CN Tower)", 43.6426, -79.3871),
        Triple("Toronto City Hall (Nathan Phillips Square)", 43.6534, -79.3841),
        Triple("Union Station Toronto", 43.6453, -79.3806),
        Triple("Royal Ontario Museum (ROM)", 43.6677, -79.3948),
        Triple("Art Gallery of Ontario (AGO)", 43.6536, -79.3925),
        Triple("CF Toronto Eaton Centre", 43.6544, -79.3807),
        Triple("Ripley's Aquarium of Canada", 43.6424, -79.3860),
        Triple("Rogers Centre Toronto", 43.6414, -79.3894),
        Triple("Scotiabank Arena Toronto", 43.6435, -79.3791),
        Triple("University of Toronto St. George Campus", 43.6629, -79.3957),
        Triple("Distillery Historic District Toronto", 43.6503, -79.3596),
        Triple("St. Lawrence Market Toronto", 43.6486, -79.3716),
        Triple("High Park Toronto", 43.6465, -79.4637),
        Triple("Harbourfront Centre Toronto", 43.6387, -79.3820),
        Triple("Yonge-Dundas Square Toronto", 43.6561, -79.3802),

        // Mississauga (56-70)
        Triple("Mississauga City Centre (Square One)", 43.5933, -79.6424),
        Triple("Mississauga Celebration Square", 43.5890, -79.6441),
        Triple("Port Credit Harbour Mississauga", 43.5501, -79.5841),
        Triple("Toronto Pearson International Airport Terminal 1", 43.6777, -79.6248),
        Triple("University of Toronto Mississauga (UTM)", 43.5479, -79.6609),
        Triple("Streetsville Main Street Mississauga", 43.5828, -79.7128),
        Triple("Kariya Park Mississauga", 43.5865, -79.6385),
        Triple("Erin Mills Town Centre Mississauga", 43.5606, -79.7161),
        Triple("Meadowvale Community Centre Mississauga", 43.5956, -79.7562),
        Triple("Lakeview Golf Course Mississauga", 43.5780, -79.5630),
        Triple("Cooksville GO Station Mississauga", 43.5835, -79.6210),
        Triple("Trillium Health Centre Mississauga", 43.5710, -79.6080),
        Triple("Jack Darling Memorial Park Mississauga", 43.5295, -79.6105),
        Triple("Rattray Marsh Conservation Area", 43.5180, -79.6025),
        Triple("Dixie Outlet Mall Mississauga", 43.5812, -79.5765),

        // Brampton (71-85)
        Triple("Brampton City Hall", 43.6853, -79.7599),
        Triple("Gage Park Brampton", 43.6845, -79.7612),
        Triple("Bramalea City Centre", 43.7153, -79.7214),
        Triple("Chinguacousy Park Brampton", 43.7275, -79.7225),
        Triple("Heart Lake Conservation Area Brampton", 43.7711, -79.7942),
        Triple("Algoma University Brampton Campus", 43.6840, -79.7590),
        Triple("Sheridan College Davis Campus Brampton", 43.6559, -79.7388),
        Triple("Brampton GO Station", 43.6872, -79.7634),
        Triple("Peel Art Gallery Museum and Archives (PAMA)", 43.6865, -79.7585),
        Triple("Mount Pleasant GO Station Brampton", 43.6750, -79.8220),
        Triple("Brampton Civic Hospital", 43.7431, -79.7340),
        Triple("Trinity Common Mall Brampton", 43.7420, -79.7180),
        Triple("Claireville Conservation Area Brampton", 43.7650, -79.6450),
        Triple("Eldorado Park Brampton", 43.6510, -79.8050),
        Triple("Shoppers World Brampton", 43.6670, -79.7370),

        // Markham (86-93)
        Triple("Markham Civic Centre", 43.8561, -79.3370),
        Triple("Historic Main Street Unionville", 43.8703, -79.3125),
        Triple("CF Markville Shopping Centre", 43.8677, -79.2845),
        Triple("Seneca Polytechnic Markham Campus", 43.8510, -79.3510),
        Triple("Markham Stouffville Hospital", 43.8825, -79.2310),
        Triple("Markham Museum", 43.8890, -79.2580),
        Triple("Pacific Mall Markham", 43.8252, -79.3061),
        Triple("Miliken Mills Community Centre Markham", 43.8260, -79.3090),

        // Vaughan & Richmond Hill (94-100)
        Triple("Vaughan Mills Shopping Centre", 43.8256, -79.5383),
        Triple("Canada's Wonderland Vaughan", 43.8430, -79.5390),
        Triple("Vaughan City Hall", 43.8340, -79.5080),
        Triple("Kortright Centre for Conservation Vaughan", 43.8320, -79.5900),
        Triple("Richmond Hill Centre for the Performing Arts", 43.8765, -79.4375),
        Triple("David Dunlap Observatory Richmond Hill", 43.8640, -79.4260),
        Triple("Richmond Green Sports Centre", 43.8990, -79.4010)
    )
}