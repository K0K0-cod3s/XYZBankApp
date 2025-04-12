FROM maven:3.9.5-eclipse-temurin-17

# Install dependencies
RUN apt-get update && apt-get install -y wget unzip curl gnupg

# Install Google Chrome
RUN wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt-get install -y ./google-chrome-stable_current_amd64.deb && \
    rm google-chrome-stable_current_amd64.deb

# Install specific ChromeDriver version
# Using a recent stable version that works with current Chrome
RUN wget -q https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/120.0.6099.109/linux64/chromedriver-linux64.zip && \
    unzip chromedriver-linux64.zip && \
    mv chromedriver-linux64/chromedriver /usr/local/bin/chromedriver && \
    chmod +x /usr/local/bin/chromedriver && \
    rm -rf chromedriver-linux64.zip chromedriver-linux64

# Verify installations
RUN google-chrome --version && chromedriver --version || true

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Run Maven tests by default
CMD ["mvn", "test"]