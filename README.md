# massiveTurtle
A GraphQL interface to gather threat information from free sources

## Current Integrations

### VirusTotal
VirusTotal has an available API that is free to use.

Apis available here:
https://developers.virustotal.com/reference/overview

Register for a virus total account and generate an API Key.

Update application.properties
```agsl
massive-turtle.source.virus-total.api-key=YOUR_API_KEY
```


## How to run massiveTurtle

MassiveTurtle is a self-contained spring boot application accisible at:

http://localhost:8080/graphiql

## Sample Queries

Get Basic information about an IP Address
```agsl
query {
  ipAddressDetails(ipAddress: "144.76.136.153") {
    ipAddress
    network
    country
    tags
    lastAnalysisStats {
      harmless
      malicious
    }
  }
}
```

Get metadata information about an IP address
```
query {  
  ipAddressInfo(ipAddress: "144.76.136.153") {
    ... on IpComment {
      comment: text
    }
    ... on IpResolution {
      host_name: hostName
    }
  }
}
```

