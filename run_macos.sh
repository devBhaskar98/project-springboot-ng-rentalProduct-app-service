#!/bin/bash

# Give execute permission: chmod +x run-all.sh

# Build the entire project first
echo "🔨 Building project..."
./mvnw clean install -DskipTests
if [ $? -ne 0 ]; then
  echo "❌ Build failed. Exiting."
  exit 1
fi

# List of services to run
SERVICES=(
  "services/config-server"
  "services/discovery-server"
  "services/mail-server"
  "services/trending-news"
)

# Array to store PIDs
PIDS=()

# Start each service in the background
for SERVICE in "${SERVICES[@]}"
do
  echo "🚀 Starting $SERVICE..."
  (cd "$SERVICE" && ./mvnw spring-boot:run) &
  PIDS+=($!)
  sleep 5
done

echo "✅ All services are starting in the background."

# Monitor PIDs
for i in "${!PIDS[@]}"
do
  PID=${PIDS[$i]}
  SERVICE=${SERVICES[$i]}
  wait $PID
  EXIT_CODE=$?
  if [ $EXIT_CODE -ne 0 ]; then
    echo "❌ Service $SERVICE failed with exit code $EXIT_CODE"
    echo "🛑 Stopping all services..."
    kill "${PIDS[@]}" 2>/dev/null
    exit 1
  fi
done
