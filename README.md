# Real-Solutions

A web application where a user can view real estate listings and submit offers, have the offers go to all appropriate parties, and the the seller can view on all offers in one area.

## Problem Domain

When a seller lists their home, many times there are multiple offers that come in.  For the agent, presenting those offers can be cumbersome and time consuming.  Having a single source of collecting these offers can make it simpler and faster in viewing these offers.  It also makes the decision making process easier and more efficient for all parties involved.

## Minimum Viable Product (MVP)

This application will allow a buyer’s agent to submit an offer to a listing on the application. The seller and seller’s agent will be able to see the submitted offers.

MVP features:

- [x] User authorization/authentication
- [x] Rendering an offer submission form
- [x] Domain Model
- [x] Property Model
- [x] Rendering all offers from submission form to a single page
- [x] Create 3 distinct user areas
    - One for the Seller’s Agent
        - Create, Read, Add, Delete
    - One for the Seller
        - Read
    - One for the Buyers agent

Stretch Goals:

- [x] Fetching and rendering data from Zillow API
- [x] Rendering all listings pulled from Zillow API
- [x] Integrate Twilio API to send text notifications to all parties
- [x] Integrate email notifications to send offer notifications to all parties (ask alex for AWS SNS intro)
- [x] File upload
