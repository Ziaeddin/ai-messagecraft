#!/bin/bash

# AI-MessageCraft Setup Script
# This script helps set up the development environment

echo "🤖 AI-MessageCraft Setup Script"
echo "================================="

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "❌ Java 17 or higher is required. Found: $JAVA_VERSION"
    exit 1
fi
echo "✅ Java $JAVA_VERSION detected"

# Check if Maven is available
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven 3.6+"
    exit 1
fi
echo "✅ Maven detected"

# Check if Docker is available (optional)
if command -v docker &> /dev/null; then
    echo "✅ Docker detected"
    DOCKER_AVAILABLE=true
else
    echo "⚠️  Docker not found (optional for development)"
    DOCKER_AVAILABLE=false
fi

# Create .env file if it doesn't exist
if [ ! -f .env ]; then
    if [ -f .env.example ]; then
        cp .env.example .env
        echo "📝 Created .env file from template"
    else
        cat > .env << EOF
# OpenAI Configuration
OPENAI_API_KEY=your-openai-api-key-here
OPENAI_MODEL=gpt-4
OPENAI_MAX_TOKENS=1000
OPENAI_TEMPERATURE=0.7

# Application Configuration
SPRING_PROFILES_ACTIVE=dev
SERVER_PORT=8080
EOF
        echo "📝 Created default .env file"
    fi
    echo "⚠️  Please edit .env and add your OpenAI API key"
else
    echo "✅ .env file already exists"
fi

# Build the project
echo "🔨 Building the project..."
if mvn clean compile > /dev/null 2>&1; then
    echo "✅ Project built successfully"
else
    echo "❌ Build failed. Please check the output above."
    exit 1
fi

# Run tests
echo "🧪 Running tests..."
if mvn test > /dev/null 2>&1; then
    echo "✅ All tests passed"
else
    echo "❌ Some tests failed. Please check the output above."
fi

echo ""
echo "🎉 Setup completed successfully!"
echo ""
echo "Next steps:"
echo "1. Edit .env file and add your OpenAI API key"
echo "2. Run the application:"
echo "   mvn spring-boot:run"
echo ""
echo "3. Access the application:"
echo "   - API: http://localhost:8080"
echo "   - Swagger UI: http://localhost:8080/swagger-ui.html"
echo "   - Health Check: http://localhost:8080/actuator/health"
echo ""

if [ "$DOCKER_AVAILABLE" = true ]; then
    echo "4. Or run with Docker:"
    echo "   docker-compose up -d"
    echo ""
fi

echo "📖 Documentation:"
echo "   - README.md - Main documentation"
echo "   - docs/ARCHITECTURE.md - System architecture"
echo "   - docs/API_EXAMPLES.md - API usage examples"
echo ""
echo "Happy coding! 🚀" 