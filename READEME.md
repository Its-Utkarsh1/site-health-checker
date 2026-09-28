# Site Health Checker

A Java CLI tool that checks a list of URLs and reports if each is up, how fast it responded, and whether SSL is valid.

## What it does

- Reads URLs from `urls.txt` (one per line)
- Sends an HTTP request to each
- Follows redirects automatically
- Reports status code and response time
- Handles failures: timeouts, unknown hosts, invalid URLs, SSL errors

## How to run

1. Add URLs to `urls.txt`, one per line
2. Run:
   mvn compile
   mvn exec:java


## Tests

4 tests covering: valid URL, invalid URL, unknown host, response time.

## What I chose not to build

A web UI, scheduling, and a database. The core job is checking URLs and reporting honestly — a UI would have eaten time I needed for error handling.

## How I know it works

Tested against 10 URLs: live sites, redirects, timeouts, invalid URLs, and expired SSL. All returned correct results. 4 tests pass.

## Project structure
