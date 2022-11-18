# Software Requirements

## Vision

When a seller lists their home, many times there are multiple offers that come in.  For the agent, presenting those offers can be cumbersome and time consuming.  Having a single source of collecting these offers can make it simpler and faster in viewing these offers.  It also makes the decision making process easier and more efficient for all parties involved.

## Scope (In/Out)

In: What will the application do.

- [x] The app will allow a user to securely login.
- [x] The app will allow for two distinct users, the agent and seller client.
- [x] The agent will be provided a form to create a listing offer submission page.
- [x] The agent will be provided a listing of all their listings.
- [x] The agent will be provided a listing of all their seller clients.
- [x] The agent will be provided a form to communicate with the seller.
- [x] The seller will be able to view all offers submitted for their property.
- [x] The seller will be able to communicate with their agent via the platform.

- Out: What will the application not do.

- [x] The app will not chart multiple listings on a map.
- [x] The app will not serve advertisements to its users
- [x] The app will not allow users who are not real estate agents or their clients.

## Minimum Viable Product

The MVP for this application will allow a buyer’s agent to submit an offer to a listing on the application, and the seller and seller’s agent to see the submitted offers.

The MVP for our project will include:

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

## Stretch Goals:

- [x] Fetching and rendering data from Zillow API
- [x] Rendering all listings pulled from Zillow API
- [x] Integrate Twilio API to send text notifications to all parties
- [x] Integrate email notifications to send offer notifications to all parties (ask alex for AWS SNS intro)
- [x] File upload



## Data Flow

The agent will create property listings from within the application.  The application will then generate an offer submission form for the property.  They will market that offer submission page in their MLS listing so that buyer’s agents can submit offers via the form.  The offer submissions will route to the backend and be stored in postgres.  Postgres will then serve the data to the front end for both the agent and seller to consume.

## Non-Functional Requirements

Agent’s brokerage name and license name must be clearly visible on public facing offer web pages in order to remain in compliance with the Department of Real Estate.

PII security controls must be in place when hosting the database via a data privacy framework.  All user data must be secured and user access must be authenticated in order to mitigate potential data breaches.
